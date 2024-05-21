SELECT * FROM spring5.tbl_person;

truncate tbl_person;

CREATE TABLE tbl_person (
	id INT(6) PRIMARY KEY,
    person_name VARCHAR(255) NOT NULL,
    person_age INT(3)
);

SELECT * FROM tbl_person
ORDER BY id DESC
;

insert into tbl_person values(100, "백백이", 10);
insert into tbl_person values(200, "이백이", 20);