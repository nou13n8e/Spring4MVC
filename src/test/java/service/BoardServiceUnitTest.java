package service;

import nou.hello.spring4.model.Board;
import nou.hello.spring4.model.Member;
import nou.hello.spring4.service.BoardService;
import nou.hello.spring4.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
                         "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class BoardServiceUnitTest {
    @Autowired BoardService bsrv;

    @Test
    public void readBoard() throws Exception {
        int cpage=1;
        List<Board> results=bsrv.readBoard(cpage);
        assertEquals(results.size(), 15);
        System.out.println(results);
    }
    @Test
    public void readOneBoard() throws Exception {
        String bno="300";
        Board result=bsrv.readOneBoard(bno);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @Transactional
    public void saveBoard() throws Exception {
        Board bd=new Board(null, "점심 뭐 먹을까요?", "abc123", null, null, "점심 뭐 먹을까요?");
        assertEquals(bsrv.saveBoard(bd), true);
    }
}
