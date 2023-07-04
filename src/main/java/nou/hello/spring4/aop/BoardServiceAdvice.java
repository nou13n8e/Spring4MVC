package nou.hello.spring4.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class BoardServiceAdvice {
    private Logger logger= LogManager.getLogger(MemberServiceAdvice.class);

    @Pointcut("execution(* nou.hello.spring4.controller.BoardController.write(..))")
    public void writePoint(){
    }
    @Around("writePoint()")
    public Object writeAOPProcess(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("writeAOPProcess 호출!");
        HttpSession sess=null;

        for(Object o : pjp.getArgs()) {
            if(o instanceof HttpSession)
                sess=(HttpSession) o;
        }

        // pointcut 대상 메서드 실행
        // 로그인 하지 않았다면 login으로 이동, 로그인 했다면 write로 이동
        if(sess.getAttribute("member") == null) return "redirect:/member/login";

        Object obj=pjp.proceed();
        return obj;
    }
}
