truncate tbl_board;

CREATE TABLE tbl_board (

	board_no INT(8) PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(100) NOT NULL,
    view_count INT(8) DEFAULT 0,
    reg_date_time DATETIME DEFAULT current_timestamp
);

SELECT * FROM tbl_board;

SELECT * FROM tbl_board
order by board_no DESC -- 내림차순
LIMIT 0, 6; -- 인덱스0부터, 6행 가져와 -- 그 다음 6개는 LIMIT 6, 6 / 12, 6 / 18, 6, ....

SELECT COUNT(*)
        FROM tbl_board;
	
SELECT * FROM tbl_board
WHERE TITLE LIKE '%3%'
ORDER BY BOARD_NO DESC;


