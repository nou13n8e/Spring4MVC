package dao;

import nou.hello.spring4.dao.MemberDAO;
import nou.hello.spring4.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml",
                         "classpath:spring/root-context.xml"})
@WebAppConfiguration
public class MemberDAOUnitTest {
    // 테스트할 객체를 주입받음
    @Autowired MemberDAO mdao;

    @Test
    @Transactional
    public void insertMember() throws Exception {
        Member m=new Member(null, "abc123", "987xyz", "abc123", "abc123@987xyz", null);
        assertEquals(mdao.insertMember(m), 1);
    }

    @Test
    public void loginMember() throws Exception {
        Member m=new Member();
        m.setUserid("abc123");
        m.setPasswd("987xyz");

        //System.out.println(mdao.loginMember(m));
        assertNotNull(mdao.loginMember(m));
    }
}

