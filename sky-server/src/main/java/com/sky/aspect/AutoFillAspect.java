package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void AutoFillPt() {
    }

    @Before("AutoFillPt()")
    public void AutoFill(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);
        OperationType opt = autoFill.value();

        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0)return;
        Object entity = args[0];
        try {
            Method creatTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method updateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method creatUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
            Method updateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            if(opt==OperationType.INSERT){
                creatTime.invoke(entity,LocalDateTime.now());
                updateTime.invoke(entity,LocalDateTime.now());
                creatUser.invoke(entity, BaseContext.getCurrentId());
                updateUser.invoke(entity,BaseContext.getCurrentId());
            }else if(opt == OperationType.UPDATE){
                updateTime.invoke(entity,LocalDateTime.now());
                updateUser.invoke(entity,BaseContext.getCurrentId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
