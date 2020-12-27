drop table admin;
create table admin
(
userid varchar2(20) primary key,
passwd varchar2(20) not null
);
insert into admin values('admin', '0000');