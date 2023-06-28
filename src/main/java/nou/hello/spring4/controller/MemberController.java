package nou.hello.spring4.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

    private Logger logger= LogManager.getLogger(MemberController.class);

    @RequestMapping(value = "/member/join", method = RequestMethod.GET)
    public String join(Model m) {

        // 로거 출력 : info(출력하고 싶은 메시지)
        logger.info("member/join 호출!");

        return "member/join.tiles";
    }
    @RequestMapping(value = "/member/join", method = RequestMethod.POST)
    public String joinok(Model m) {

        logger.info("member/joinok 호출!");

        return "redirect:/member/login";
    }
    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String login(Model m) {

        logger.info("member/login 호출!");

        return "member/login.tiles";
    }
    @RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public String loginok(Model m) {

        logger.info("member/loginok 호출!");

        return "redirect:/member/info";
    }
    @RequestMapping("/member/info")
    public String info(Model m) {

        logger.info("member/info 호출!");

        return "member/info.tiles";
    }
}
