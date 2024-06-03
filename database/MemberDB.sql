SELECT * FROM spring5.tbl_member;

DROP TABLE tbl_member; -- 댓글테이블 구조까지 삭제

-- 회원 관리 테이블
CREATE TABLE tbl_member (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL, -- 암호화해야 하기 때문에 길이를 넉넉하게 준다.
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE, -- 중복안됨
    auth VARCHAR(20) DEFAULT 'COMMON', -- 기본 권한 COMMON
    reg_date DATETIME DEFAULT current_timestamp,
    CONSTRAINT pk_member PRIMARY KEY (account)
    
    -- 가입시 account, password, name, email 입력해야 한다.
);

-- account admin을 관리자로 설정!
UPDATE tbl_member
SET auth = 'ADMIN'
WHERE account = 'admin';

commit;

-- 자동로그인 관련 컬럼 추가
-- 쿠키에 저장한 값, 자동로그인 만료시간
ALTER TABLE tbl_member
ADD (session_id varchar(255) default 'none'); -- 자동로그인 했을 경우 랜덤 쿠키값 일단 none

ALTER TABLE tbl_member
ADD (limit_time DATETIME default current_timestamp); -- 자동로그인 됐을 경우 유지기간 - 90일

SELECT * FROM spring5.tbl_member;

 
