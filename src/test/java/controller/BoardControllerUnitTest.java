package controller;

import nou.hello.spring4.controller.IndexController;
import nou.hello.spring4.controller.MemberController;
import nou.hello.spring4.model.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
                          "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class BoardControllerUnitTest {
    @Autowired WebApplicationContext wctx;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.wctx).build();
    }
    //테스트 수행
    @Test
    public void listTest() throws Exception {
        MvcResult mvcResult=mockMvc.perform(get("/board/list")
                        .param("cpg", "1"))
                .andExpect(model().attributeExists("allpg"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getModelAndView());
    }
    @Test
    public void viewTest() throws Exception {
        MvcResult mvcResult=mockMvc.perform(get("/board/view")
                        .param("bno", "300"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getModelAndView());
    }
    @Test
    public void writeTest() throws Exception {
        mockMvc.perform(get("/board/write"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/write.tiles"));

    }
    @Test
    @Transactional
    public void writeokTest() throws Exception {
        mockMvc.perform(post("/board/write")
                        .param("title", "오후에도 힘냅시다!")
                        .param("userid", "abc123")
                        .param("contents", "오후에도 힘냅시다!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/board/list"));
    }


}
