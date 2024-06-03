SELECT * FROM spring5.tbl_reply;

-- 댓글 테이블 생성
CREATE TABLE tbl_reply (
	reply_no INT(8) PRIMARY KEY AUTO_INCREMENT,
    reply_text VARCHAR(1000) NOT NULL,
    reply_writer VARCHAR(100) NOT NULL,
    reply_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- 실무에서 모든 데이터에는 생성일, 작성자, 수정일, 수정자를 기입해야 한다.
    board_no INT(8), -- 일대다관계이기에, TBL_BOARD 의 PK 값을 FK로 가지고 있어야 한다.
    constraint fk_reply
    foreign key (board_no) references tbl_board (board_no)
    ON DELETE cascade -- 원본게시글이 지워지면 댓글도 다 삭제
    );
    
    DROP TABLE tbl_reply; -- 댓글테이블 구조까지 삭제
    
    SELECT * FROM tbl_reply;
    
    select count(*) from tbl_reply
    where board_no = 5
    ;
    
    -- 게시물 별 댓글의 총 개수
    SELECT 
		B.board_no,
        B.title,
        B.content,
        B.writer,
        B.reg_date_time,
        B.view_count,
        COUNT(R.reply_no) as reply_count
    FROM tbl_board B
    join tbl_reply R
    on B.board_no = R.board_no
    group by B.board_no
    order by B.board_no desc
    ;
    
    -- 첫번째 댓글의 위치
    select * from tbl_reply
    where reply_no = 2;
    
    SELECT board_no
        FROM tbl_reply
        WHERE reply_no = 5003;
    
    select * from tbl_reply 
where board_no = 101;

-- 게시글테이블과 댓글테이블에 회원 PK컬럼 추가
ALTER TABLE tbl_reply
ADD (account VARCHAR(50)); 

-- 기존게시글 글쓴이 admin으로 변경
UPDATE tbl_reply
SET account = 'admin'
WHERE account IS NULL;
    
    