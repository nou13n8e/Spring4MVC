package nou.hello.spring4.dao;

import nou.hello.spring4.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    // sql.properties에서 작성한 sql 불러오기
    @Value("#{sql['insertMember']}") private String insertSQL;
    @Value("#{sql['loginMember']}") private String loginSQL;
    @Value("#{sql['selectOneMember']}") private String selectOneSql;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertMember(Member m) {
        // 매개변수 정의
        Object[] params=new Object[] {
                m.getUserid(), m.getPasswd(), m.getName(), m.getEmail()
        };

        // 쿼리 실행 : update(sql, 매개변수)
        return jdbcTemplate.update(insertSQL, params);
    }
    public Member loginMember(Member m) {
        // 매개변수 정의
        Object[] params=new Object[] {
                m.getUserid(), m.getPasswd()
        };

        // 로그인 매퍼 선언 - 콜백
        RowMapper<Member> mapper=new LoginMapper();

        // 쿼리 실행 : select(sql, 매개변수, 매퍼) - 단일값 반환
        // m=jdbcTemplate.queryForObject(loginSQL, params, mapper);

        // 단 결과가 없거나 둘 이상인 경우 예외 발생 - JDK8 기능 중 Optional 활용 / query 사용
        // 여기서는 query(sql, 매개변수, 매퍼)로 변경 - (리스트 기반) 다중값 반환
        List<Member> results=jdbcTemplate.query(loginSQL, params, mapper);
        m=results.isEmpty() ? null : results.get(0);
        return m;
    }

    private class LoginMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int num) throws SQLException {
            Member m=new Member();
            m.setUserid(rs.getString(1));
            m.setName(rs.getString(2));
            return m;
        }
    }

    public Member selectOneMember(String userid) {
        Object[] params=new Object[] {
                userid
        };

        RowMapper<Member> mapper=new MemberMapper();

        return jdbcTemplate.queryForObject(selectOneSql, params, mapper);
    }

    private class MemberMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int num) throws SQLException {
            Member m=new Member(
                    rs.getString(1),
                    rs.getString(2),
                    null,
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );

            return m;
        }
    }
}
