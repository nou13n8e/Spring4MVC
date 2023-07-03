package nou.hello.spring4.controller;

import nou.hello.spring4.model.Board;
import nou.hello.spring4.model.Member;
import nou.hello.spring4.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired BoardService bsrv;
    private Logger logger= LogManager.getLogger(BoardController.class);

    @GetMapping("/list")
    public String list(Model m, int cpg) {
        logger.info("board/list 호출!");
        m.addAttribute("boards", bsrv.readBoard(cpg));
        m.addAttribute("allpg", bsrv.countAllPage()); // 페이지 총합을 구하는 함수
        m.addAttribute("psnum", 10*((cpg-1)/10)+1);  //페이지네이션 시작할 번호(1, 11, 21)
        return "board/list.tiles";
    }
    @GetMapping("/write")
    public String write(HttpSession sess) {
        logger.info("board/write 호출!");
        return "board/write.tiles";
    }
    @PostMapping(value = "/write")
    public String writeok(Board bd) {
        logger.info("member/writeok 호출!");
        String returnPage="redirect:/board/fail";
        if(bsrv.saveBoard(bd)) {
            returnPage = "redirect:/board/list?cpg=1";
        };
        return returnPage;
    }
    @GetMapping("/view")
    public String view(Model m, String bno) {
        logger.info("board/view 호출!");
        m.addAttribute("board", bsrv.readOneBoard(bno));
        return "board/view.tiles";
    }

}
