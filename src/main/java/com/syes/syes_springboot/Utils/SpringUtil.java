package com.syes.syes_springboot.Utils;

import org.springframework.beans.BeansException;

import static cn.hutool.extra.spring.SpringUtil.getBeanFactory;

public class SpringUtil {
    /**
     * 获取类型为requiredType的对象
     *
     * @param name
     * @return
     * @throws org.springframework.beans.BeansException
     */
    public static <T> T getBean(Class<T> name) throws BeansException {
        if (getBeanFactory() == null) {
            return null;
        } else {
            T result = (T) getBeanFactory().getBean(name);
            return result;
        }
    }

}
