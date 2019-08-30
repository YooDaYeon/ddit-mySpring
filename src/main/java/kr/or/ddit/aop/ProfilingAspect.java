package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {

	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
	}
	
	@Around("dummy()") //dummy메서드 기준으로 Around..? 시 실행하라
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//business logic 실행 전
		long start = System.currentTimeMillis(); //시작 시간
		logger.debug("start : {}",start);
		logger.debug("Profiling around method before");
		
		//business logic 실행
		logger.debug("method name : {}", joinPoint.getSignature().getName());
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		//business logic 실행 후 
		logger.debug("Profiling around method after");
		long end = System.currentTimeMillis(); //끝나는 시간
		logger.debug("end : {}",end);
		logger.debug("실행시간 : {}",(end-start));
		
		return returnObj;
	}
	
	
	
	
}
