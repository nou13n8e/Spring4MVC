package service;

import nou.hello.spring4.controller.MemberController;
import nou.hello.spring4.model.Member;
import nou.hello.spring4.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
                         "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class MemberServiceUnitTest {
    @Autowired MemberService msrv;

    @Test
    @Transactional
    public void insertMember() throws Exception {
        Member m=new Member(null, "abc123a", "987xyz", "abc123", "abc123@987xyz", null);
        assertEquals(msrv.saveMember(m), true);
    }

    @Test
    public void loginMember() throws Exception {
        Member m=new Member();
        m.setUserid("abc123");
        m.setPasswd("987xyz");
        assertEquals(msrv.loginMember(m), true);
    }

    @Test
    public void readOneMember() throws Exception {
        String userid="abc123";
        assertNotNull(msrv.readOneMember(userid));
    }

}
