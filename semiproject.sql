--회원 테이블
create table member (
    mno int auto_increment,
    userid varchar2(18) unique,
    passwd varchar2(18) not null,
    name varchar2(10) not null,
    email varchar2(50) not null,
    regdate datetime default current_timestamp,
    primary key (mno)
);
--게시판 테이블
create table board (
    bno int auto_increment,
    title varchar2(100) not null,
    userid varchar2(18) not null,
    regdate datetime default current_timestamp,
    views int default 0,
    contents text not null,
    primary key (bno),
    foreign key (userid) references member(userid)
);