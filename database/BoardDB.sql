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