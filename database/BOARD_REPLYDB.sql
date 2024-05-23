SELECT * FROM spring5.tbl_reply;

-- 댓글 테이블 생성
CREATE TABLE TBL_REPLY (
	REPLY_NO INT(8) PRIMARY KEY AUTO_INCREMENT,
    REPLY_TEXT VARCHAR(1000) NOT NULL,
    REPLY_WRITER VARCHAR(100) NOT NULL,
    REPLY_DATE DATETIME DEFAULT CURRENT_TIMESTAMP, -- 실무에서 모든 데이터에는 생성일, 작성자, 수정일, 수정자를 기입해야 한다.
    BOARD_NO INT(8), -- 일대다관계이기에, TBL_BOARD 의 PK 값을 FK로 가지고 있어야 한다.
    constraint FK_REPLY
    foreign key (BOARD_NO) references TBL_BOARD (BOARD_NO)
    ON DELETE cascade -- 원본게시글이 지워지면 댓글도 다 삭제
    );
    
    DROP TABLE TBL_REPLY; -- 댓글테이블 구조까지 삭제
    
    SELECT * FROM tbl_reply;