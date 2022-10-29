package com.example.uproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect // AOP 선언 : 부가기능을 주입하는 클래스
@Component // IOC 컨테이너가 해당 객체를 생성 및 관리하도록
@Slf4j
public class DebuggingAspect {
    // 어느 기능을 대상 메소드로 선택할지 : CommentService#create()
    // * 로 진행시 모든 메소드에 적용
    @Pointcut("execution(* com.example.uproject.service.CommentService.*(..))")
    private void cut(){}

    // 실행 시점 설정 .. cut 메소드 실행 전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { // cut 의 대상 메소드
        //입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        //입력값 로딩 CommentService#create() 의 입력값 =>
        for (Object obj : args ){
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    // 실행 시점 설정 :  cut 호출 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingRetrunValue(JoinPoint joinPoint,
                                   Object returnObj){ // 리턴값
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();
        //반환값 로딩
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);

    }
}
