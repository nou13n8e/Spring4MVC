package nou.hello.spring4.dao;

import nou.hello.spring4.model.Member;

public interface MemberDAO {
    int insertMember(Member m);
    Member loginMember(Member m);

}
