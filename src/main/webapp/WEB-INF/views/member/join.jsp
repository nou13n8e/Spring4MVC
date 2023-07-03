<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 세션 객체가 있을 경우 회원정보 페이지로 이동 --%>
<%-- <c:if test="${not empty sessionScope.member}">
    <script>location.href="/member/info";</script></c:if> --%>

<main>
    <h2>회원가입</h2>
    <form name="joinfrm">
        <div>
            <label for="userid">아이디</label>
            <input type="text" name="userid" id="userid">
        </div>
        <div>
            <label for="passwd">비밀번호</label>
            <input type="password" name="passwd" id="passwd">
        </div>
        <div>
            <label for="repasswd">비밀번호 확인</label>
            <input type="password" name="repasswd" id="repasswd">
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" name="name" id="name">
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="email" name="email" id="email">
        </div>
        <div>
            <label></label>
            <button type="button" class="btn btn-primary" id="joinbtn">입력완료</button>
            <button type="reset" class="btn btn-danger">입력취소</button>

        </div>
    </form>
</main>
<script src="/assets/js/member.js"></script>