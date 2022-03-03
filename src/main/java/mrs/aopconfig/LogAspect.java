package mrs.aopconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	 @Pointcut("execution(* mrs.domain.service.reservation..*(..))")
	    public void pointcut() {
	    }

//	    @Before("pointcut()")
//	    public void before(JoinPoint joinPoint) {
//	        System.out.println("=====before advice starts=====");
//	        System.out.println(getMethodName(joinPoint));
//	        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
//	        System.out.println("=====before advice ends=====");
//	    }
//	    @After("pointcut()")
//	    public void after(JoinPoint joinPoint) {
//	        System.out.println("=====after advice starts=====");
//	        System.out.println(getMethodName(joinPoint));
//	        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
//	        System.out.println("=====after advice ends=====");
//	    }
	 
	 @AfterReturning(pointcut = "pointcut()", returning = "result")
	 public void afterReturning(JoinPoint joinPoint, Object result) {
	     System.out.println("=====after returning advice starts=====");
	     if (result != null) {
		     System.out.println(getMethodName(joinPoint).toString());
	         System.out.println(result);
	     }else {
	    	 System.out.println(getMethodName(joinPoint).toString());
	     }
	     System.out.println("=====after returning advice ends=====");
	 }

	    private String getMethodName(JoinPoint joinPoint) {
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        return signature.getName();
	    }

		@Override
		public String toString() {
			return "LogAspect []";
		}
	    
	    
}
