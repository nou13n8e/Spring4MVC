package nou.hello.spring4.controller;

import nou.hello.spring4.model.Member;
import nou.hello.spring4.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private Logger logger= LogManager.getLogger(MemberController.class);
    @Autowired
    private MemberService msrv;

    @RequestMapping(value = "/member/join", method = RequestMethod.GET)
    public String join(Model m) {

        // 로거 출력 : info(출력하고 싶은 메시지)
        logger.info("member/join 호출!");

        return "member/join.tiles";
    }
    @RequestMapping(value = "/member/join", method = RequestMethod.POST)
    public String joinok(Member m) {
        logger.info("member/joinok 호출!");
        String viewName="redirect:/member/joinfail";
        if(msrv.saveMember(m)){
            viewName="redirect:/member/login";
        };

        return viewName;
    }
    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String login(Model m) {

        logger.info("member/login 호출!");

        return "member/login.tiles";
    }
    @RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public String loginok(Member m, HttpSession sess) {
        logger.info("member/loginok 호출!");
        String viewName="redirect:/member/loginfail";
        if(msrv.loginMember(m)) {
            sess.setAttribute("member", m);
            viewName = "redirect:/member/info";
        };
        return viewName;
    }
    @RequestMapping("/member/info")
    public String info(Model m, HttpSession sess) {
        logger.info("member/info 호출!");
        String userid=
                ((Member) sess.getAttribute("member")).getUserid();
        m.addAttribute("member", msrv.readOneMember(userid));

        return "member/info.tiles";
    }
}
