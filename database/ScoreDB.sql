create table tbl_score(
stu_num int(8) primary key auto_increment, 
stu_name varchar(255) not null, 
kor int(3) not null, 
eng int(3) not null, 
math int(3) not null, 
total int(3), 
average float(5, 2), 
grade char(1)
); 

select * from tbl_score;