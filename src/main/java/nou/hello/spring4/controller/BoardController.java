package nou.hello.spring4.controller;

import nou.hello.spring4.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired BoardService bsrv;
    private Logger logger= LogManager.getLogger(BoardController.class);

    @GetMapping("/list")
    public String list(Model m, int cpg) {
        logger.info("board/list 호출!");
        m.addAttribute("boards", bsrv.readBoard(cpg));
//        m.addAttribute("psnum", );  //페이지네이션 시작할 번호(1, 11, 21)
//        m.addAttribute("allpg", );  //페이지 총합을 구하는 함수를 이용

        return "board/list.tiles";
    }
    @GetMapping("/write")
    public String write() {
        logger.info("board/write 호출!");

        return "board/write.tiles";
    }
    @GetMapping("/view")
    public String view(Model m, String bno) {
        logger.info("board/view 호출!");
        m.addAttribute("board", bsrv.readOneBoard(bno));
        return "board/view.tiles";
    }

}
