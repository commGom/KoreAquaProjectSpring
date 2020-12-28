drop table qna purge;

create table qna
( num NUMBER(4) primary key,
 title VARCHAR2(200) not null,
 author VARCHAR2(20) not null,
 email VARCHAR2(50) not null,
 passwd VARCHAR2(20) not null,
 content VARCHAR2(4000) not null,
 filename VARCHAR2(50),
 writeday DATE default SYSDATE,
 readCnt NUMBER(4) default 0,
 repRoot NUMBER(4),
 repStep NUMBER(4),
 repIndent NUMBER(4));

drop sequence qna_seq;
create sequence qna_seq
increment by 1
start with 0
minvalue 0
maxvalue 9999
nocache
nocycle;

insert into qna(num, title, author, email, passwd, content, filename, repRoot, repStep, repIndent)
values(qna_seq.nextval, '테스트', '홍길동', 'hong@naver.com', '1111', '테스트입니다', null, qna_seq.currval, 0, 0);
commit;