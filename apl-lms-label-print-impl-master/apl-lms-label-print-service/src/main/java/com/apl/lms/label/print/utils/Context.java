package com.apl.lms.label.print.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hjr start
 * @date 2020/5/28 - 14:21
 */
    @Component
    public class Context {


        public static HttpServletRequest getRequest() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return attributes.getRequest();
        }

        public static HttpServletResponse httpServletResponse() {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return requestAttributes.getResponse();
        }

        public static String getURI() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String uri = attributes.getRequest().getRequestURI();
            return uri;
        }

        public static String getHeader(String key) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String token = attributes.getRequest().getHeader(key);
            return token;
        }
    }
