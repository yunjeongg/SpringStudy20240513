SELECT * FROM spring5.tbl_board;

truncate tbl_board;

CREATE TABLE tbl_board (
	board_no INT(8) PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(100) NOT NULL,
    view_count INT(8) DEFAULT 0,
    reg_date_time DATETIME DEFAULT current_timestamp
);

SELECT * FROM tbl_board
order by board_no DESC;

SELECT * FROM tbl_board
order by board_no DESC -- 내림차순
LIMIT 0, 6; -- 인덱스0부터, 6행 가져와 -- 그 다음 6개는 LIMIT 6, 6 / 12, 6 / 18, 6, ....

SELECT COUNT(*)
        FROM tbl_board;
	
SELECT * FROM tbl_board
WHERE TITLE LIKE '%3%'
	OR CONTENT LIKE '%3%'
ORDER BY BOARD_NO DESC;

-- 게시글테이블과 댓글테이블에 회원 PK컬럼 추가
ALTER TABLE tbl_board
ADD (account VARCHAR(50));


-- 기존게시글 글쓴이 admin으로 변경
UPDATE tbl_board
SET account = 'admin'
WHERE account IS NULL;

ALTER TABLE tbl_board
ADD CONSTRAINT fk_board_member
FOREIGN KEY (account)
REFERENCES tbl_member (account);
-- ON DELETE CASCADE; -- 이 문구 추가시 회원탈퇴시 이 계정이 쓴 게시물 모두 삭제

-- 상세게시글의 작성자를 보드어카운트와 맞게바꾸기
select a.writer, b.account 
from tbl_board A
join tbl_board B
on a.account = b.account;

SELECT 
	B.board_no, B.title, M.name AS writer
    , B.content, B.view_count
    , B.reg_date_time, M.account 
FROM tbl_board B
LEFT OUTER JOIN tbl_member M
ON B.account = M.account
WHERE board_no = 100
ORDER BY board_no DESC
;

-- writer 과 account 일치시키기
SELECT 
	B.board_no, B.title, M.name AS writer
    , B.content, B.view_count
    , B.reg_date_time, M.account 
FROM tbl_board B
LEFT OUTER JOIN tbl_member M
ON B.account = M.account
WHERE board_no = 100
ORDER BY board_no DESC
;




