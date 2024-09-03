package com.trupper.examen.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class TimeExecutionAspect {
	
	
	@Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object tiempoDeEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();          
        long executionTime = System.currentTimeMillis() - start;
        
        log.info(joinPoint.getSignature() + " tardo  " + executionTime + "ms");
        
        return result;
    }

}
