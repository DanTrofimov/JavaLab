package ru.itis.trofimoff.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.service.MethodService;

@Component
@Aspect
public class MethodAspect {
    @Autowired
    private MethodService methodService;

    @Around("within(ru.itis.trofimoff.repository..*) || within(ru.itis.trofimoff.controller..*)")
    public void addTimeToResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        String signature = joinPoint.getSignature().toShortString().split("\\.")[1];
        methodService.incrementMethod(signature.substring(0, signature.length() - 2));
    }
}
