package com.syes.syes_springboot.config;

import com.syes.syes_springboot.Utils.JwtUtil;
import com.syes.syes_springboot.entity.User;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("Token已拦截请求");
        //token不存在直接报错
        if (token == null) {
            throw new BusinessException("Token不存在");
        }
        String userid = JwtUtil.getAudience(token); //获取签发对象的Userid
        User RealUser = userMapper.selectById(userid);
        //姓名不匹配返回
        if (!Objects.equals(RealUser.getRealname(), JwtUtil.getClaimByName(token, "username").asString())) {
            throw new BusinessException("认证错误");
        }
        if (JwtUtil.checkDate(token)) {
            throw new BusinessException("499", "token过期");
        }
        //布尔值验证
        return JwtUtil.vertifyToken(token, RealUser.getId(), RealUser.getPassword());
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
