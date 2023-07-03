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
public class MemberServiceAdvice {
    private Logger logger= LogManager.getLogger(MemberServiceAdvice.class);

    @Pointcut("execution(* nou.hello.spring4.controller.MemberController.info(..))")
    public void infoPoint() {
    }
    @Pointcut("execution(* nou.hello.spring4.controller.MemberController.join(..))")
    public void joinPoint() {
    }
    @Pointcut("execution(* nou.hello.spring4.controller.BoardController.write(..))")
    public void writePoint(){
    }
    @Around("infoPoint()")
    public Object infoAOPProcess(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("infoAOPProcess 호출!");
        HttpSession sess=null;

        // join point에서 넘겨준 매개변수에서 필요한 객체만 추출
        for(Object o : pjp.getArgs()) {
            if(o instanceof HttpSession)
                sess=(HttpSession) o;
        }

        // pointcut 대상 메서드 실행
        if(sess.getAttribute("member") == null) return "redirect:/member/login";

        Object obj=pjp.proceed();
        return obj;
    }
    @Around("joinPoint()")
    public Object joinAOPProcess(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("joinAOPProcess 호출!");
        HttpSession sess=null;

        for(Object o : pjp.getArgs()) {
            if(o instanceof HttpSession)
                sess=(HttpSession) o;
        }

        // pointcut 대상 메서드 실행
        // 로그인 하지 않았다면 join으로 이동, 로그인 했다면 info로 이동
        if(sess.getAttribute("member") != null) return "redirect:/member/info";

        Object obj=pjp.proceed();
        return obj;
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
        // 로그인 하지 않았다면 write으로 이동, 로그인 했다면 login로 이동
        if(sess.getAttribute("member") == null) return "redirect:/member/login";

        Object obj=pjp.proceed();
        return obj;
    }
}
