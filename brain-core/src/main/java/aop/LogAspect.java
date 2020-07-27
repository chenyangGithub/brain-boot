package aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class LogAspect {
    @Around("bean(*ServiceImpl)")
    public void logAroundMethod(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        StringBuilder methodStartInfo = new StringBuilder();
        methodStartInfo.append(className)
                .append(" ")
                .append(methodName)
                .append(" ")
                .append("start");
        if(args != null && args.length>0){
            methodStartInfo.append(", params: ");
            Arrays.stream(args).forEach(n ->{
                methodStartInfo.append(n.toString()).append(" ");
            });
        }
        log.info(methodStartInfo.toString());
        point.proceed();
        StringBuilder methodEndInfo = new StringBuilder();
        methodEndInfo.append(className)
                .append(" ")
                .append(methodName)
                .append(" ")
                .append("end");
        log.info(methodEndInfo.toString());
        




    }

}
