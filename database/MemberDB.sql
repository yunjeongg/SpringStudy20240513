SELECT * FROM spring5.tbl_member;

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