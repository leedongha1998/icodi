select * from member;
select * from notice;
select * from notice where notice_no = 1;
select * from inquire order by inquire_date desc;
select * from v$version;
select instance_name, version, status from v$instance;
insert into notice (notice_no, notice_title, notice_content,notice_date,notice_writer) values(1,'네이버페이로 구매 시 주문 확인 안내','안녕하세요? 코디북입니다.
코디북에서 네이버페이로 구매 할 경우 구매내역을 확인하기 위해서는 네이버에서 확인하셔야 합니다. :)
네이버 아이디로 로그인 후 구매하시게 되기 때문에 코디북에서는 비회원으로 구매하신 것으로 인식되기 때문이랍니다.
코디북에서 사용하시는 아이디/비밀번호로 로그인 하더라도 주문 내역이 확인되지 않으며 주문은 정상적으로 접수 되었기 때문에 이후의 배송 상황은 네이버에서 확인 부탁 드릴게요.
감사합니다.',default,'관리자');

insert into notice (notice_no, notice_title, notice_content,notice_date,notice_writer) values(1,'네이버페이로 주문 확인 방법 안내.','안녕하세요? icodi입니다.

icodi에서 네이버페이로 구매 할 경우 구매내역을 확인하기 위해서는 네이버에서 확인하셔야 합니다. :)

네이버 아이디로 로그인 후 구매하시게 되기 때문에 코디북에서는 비회원으로 구매하신 것으로 인식되기 때문이랍니다.

icodi에서 사용하시는 아이디/비밀번호로 로그인 하더라도 주문 내역이 확인되지 않으며 주문은 정상적으로 접수 되었기 때문에 이후의 배송 상황은 네이버에서 확인 부탁 드릴게요.

감사합니다.',default,'관리자');

insert into notice (notice_no, notice_title, notice_content,notice_date,notice_writer) values(8,'포인트와 적립금은 어떻게 다른가요?',' 포인트는 다음 등급으로 올라가기 위한 점수이며 적립금은 쇼핑할 때 보태어 현금처럼 사용할 수 있습니다.',default,'관리자');
commit;
select * from notice;
delete from notice where notice_no = 1;
commit;

-- inquire 테이블(1:1문의)
create table inquire(
inquire_no number,
writer varchar2(50) not null,
inquire_title varchar2(100) not null,
inquire_content varchar2(4000) not null,
inquire_date date default sysdate,
inquire_type varchar2(20) not null,
constraint  pk_inquire_no primary key(inquire_no),
constraint fk_inquire_writer foreign key(writer) references member(member_id) on delete cascade,
constraint ck_inquire_type check(inquire_type in ('A','B','C','D','E','F'))
);

select * from inquire;
select * from notice;
delete from notice where notice_no = 3;


create sequence seq_inquire_no;

insert into inquire (inquire_no,writer,inquire_title,inquire_content,inquire_date,inquire_type) values (seq_inquire_no.nextval,'honggd','제목','내용',default,'A');
insert into inquire (inquire_no,writer,inquire_title,inquire_content,inquire_date,inquire_type) values (seq_inquire_no.nextval,'honggd','제목2','내용2',default,'A');

select * from inquire where writer = 'honggd';
select * from member;
select * from inquire;
select * from inquire_comment;
commit;

create table inquire_comment(
    answer_no number,
    inquire_no number not null,
    answer_writer varchar2(50),
    answer_content varchar2(4000) not null,
    answer_date date default sysdate,
    constraint pk_answer_no primary key(answer_no),
    constraint fk_inquire_no foreign key(inquire_no) references inquire(inquire_no) on delete cascade
);
--drop table inquire;
create sequence seq_answer_no;
--delete from inquire where inquire_no = 5;
--select * from inquire_comment where inquire_no = ?

insert into inquire_comment
values(1,11,'admin','answerrrrrr',default);
commit;

select * from inquire_comment;
--delete from inquire_comment where answer_no = ?
commit;
select * from member;
--update member set MEMBER_PWD = '1234' where member_name = '이동하';
--delete from member where member_name = '이동하';
commit;
select * from inquire_comment;
--delete from inquire_comment where inquire_no = 15 and answer_no = 10;

create table alarm(
    no number,
    member_id varchar2(50) not null,
    alarm_date date default sysdate,
    board_no number default 0, 
    alarm_message varchar2(100) not null,
    constraint pk_alarm_no primary key(no),
    constraint fk_member_id foreign key(member_id) references member(member_id) on delete cascade
);
create sequence seq_alarm_no;
select * from board;
select * from alarm;

insert into alarm (no,member_id,alarm_date,board_no,alarm_message) values(seq_alarm_no.nextval,'eedongha1',default,0,'gd');
--delete from alarm;
--insert into alarm (no,member_id,alarm_date,alarm_message) values(0,'eedongha1',default,'ㅎㅇㅎㅇ');
--insert into alarm (no,member_id,alarm_date,alarm_message) values(seq_alarm_no.nextval,'eedongha1',default,'ㅎㅇㅎㅇ2');
--select alarm_message, alarm_date from alarm where member_id = 'eedongha1';
commit;

create table stat(
    visit_member_id varchar2(50),
    visit_date date default sysdate,
    constraint fk_visit_member_id foreign key(visit_member_id) references member(member_id) on delete cascade
);
--drop table stat;
select * from stat;
-- 중복 아이디 제거
select distinct visit_member_id from stat;
select
    *
from(
select 
    row_number() over(order by visit_member_id desc) rnum,
    m.*
    from stat m 
    ) m
where
    to_char(visit_date,'YYYY-MM-DD') = '2022-07-19';
    
select distinct (visit_member_id) from stat where to_char(visit_date, 'YYYY-MM-DD') = '2022-07-19';

-- 특정날짜 조희
SELECT count(distinct (visit_member_id))cnt FROM stat WHERE TO_CHAR(visit_date, 'YYYYMMDD') = '20220719';

select * from codi_board;
-- 날짜별 코디 개수 조회 쿼리
select count(*) from codi_board where to_char(reg_date,'YYYY-MM-DD' )= '2022-07-19';
select * from codi_board;
select * from alarm;

select * from alarm where alarm_message = 'gd';
select * from product;