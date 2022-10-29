package com.example.uproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
public class PerformanceAspect {
    // 특정 어노테이션을 대상 지정
    @Pointcut("@annotation(com.example.uproject.annotation.RunningTime)")
    private void enableRunnigTime(){}

    //기본 패키지의 모든 메소드
    @Pointcut("execution(* com.example.uproject..*.*(..))")
    private void cut() {};

    //실행 시점 설정 : 두 조건을 모두 만족하는 대상을 전후로 부가 기능 삽입
    @Around("cut() && enableRunnigTime()")
    public void loggingRunnigTime(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch(); // 메소드 수행 전 측정 시작
        stopWatch.start();

        Object returningObj = joinPoint.proceed(); // 메소드 수행

        String methodName = joinPoint.getSignature()         //메소드명
                .getName();

        stopWatch.stop();; // 메소드 수행 후 측정 종료 + 로깅
        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }
}
