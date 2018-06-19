package com.example.demo1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
public class AuthCheckAop {

    @Pointcut("@annotation(com.example.demo1.annotation.AuthCheck)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object authcheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String token = pathVariables.get("token");
        if (token== null || !token.equals("123456")) {
            return "access denied";
        }
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}