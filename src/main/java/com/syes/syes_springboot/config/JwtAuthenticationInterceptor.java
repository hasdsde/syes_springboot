package com.syes.syes_springboot.config;

import com.syes.syes_springboot.Utils.JwtUtil;
import com.syes.syes_springboot.entity.Rootuser;
import com.syes.syes_springboot.entity.User;
import com.syes.syes_springboot.mapper.RootuserMapper;
import com.syes.syes_springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RootuserMapper rootuserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String Turl = request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        System.out.println("拦截请求地址：" + Turl);

        //放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        //获取token
        String token = request.getHeader("token");
        //token不存在直接报错
        if (token == null) {
            throw new BusinessException("Token不存在");
        }
        //获取签发对象id，不存在直接报错
        String userid = null; //获取签发对象的Userid
        try {
            userid = JwtUtil.getAudience(token);//获取token中的签发对象
        } catch (Exception e) {
            throw new BusinessException("499", "数据校验失败");
        }
        //带着token验证载荷username是否正确
        User RealUser = null;
        Rootuser rootUser = null;
        if (userid.equals("root")) {
            rootUser = rootuserMapper.selectById(userid);
            if (!Objects.equals(rootUser.getUsername(), JwtUtil.getClaimByName(token, "username").asString())) {
                throw new BusinessException("认证错误");
            }
        } else {
            RealUser = userMapper.selectById(userid);
            //姓名不匹配返回
            if (!Objects.equals(RealUser.getRealname(), JwtUtil.getClaimByName(token, "username").asString())) {
                throw new BusinessException("认证错误");
            }
        }


        //检查是否过期
        if (JwtUtil.checkDate(token)) {
            throw new BusinessException("499", "token过期");
        }
        //布尔值验证
        if (userid.equals("root")) {
            return JwtUtil.vertifyRootToken(token, userid, rootUser.getPassword(), rootUser.getUsername());
        } else {
            return JwtUtil.vertifyToken(token, userid, RealUser.getPassword());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
