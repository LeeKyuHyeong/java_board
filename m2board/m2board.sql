
/* Drop Tables */

DROP TABLE tbl_board CASCADE CONSTRAINTS;
DROP TABLE tbl_member CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_board;




/* Create Sequences */

CREATE SEQUENCE seq_board;



/* Create Tables */

CREATE TABLE tbl_board
(
	no number NOT NULL,
	title varchar2(100) NOT NULL,
	content varchar2(4000) NOT NULL,
	regdate date DEFAULT sysdate NOT NULL,
	readcount number DEFAULT 0 NOT NULL,
	id varchar2(30) NOT NULL,
	PRIMARY KEY (no)
);


CREATE TABLE tbl_member
(
	id varchar2(30) NOT NULL,
	password varchar2(120) NOT NULL,
	name varchar2(30) NOT NULL,
	birth date,
	phone varchar2(20),
	zipcode varchar2(6),
	address1 varchar2(120),
	address2 varchar2(60),
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE tbl_board
	ADD FOREIGN KEY (id)
	REFERENCES tbl_member (id)
;

SELECT * FROM tbl_board;
SELECT * FROM tbl_member;

SELECT b.no, b.title, b.id, b.regdate, b.readcount, m.name
FROM tbl_board b join tbl_member m
ON b.id = m.id
ORDER BY no DESC;

DELETE FROM TBL_BOARD WHERE no = 2;

SELECT rownum, title FROM tbl_board;

-- 페이징 처리
SELECT B.*
        FROM (SELECT rownum AS rnum, A.*
              FROM (SELECT b.no, b.title, m.id, 
                           case when to_char(b.regdate, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD') 
                                then to_char(b.regdate, 'HH24:MI:SS')  
                                else to_char(b.regdate, 'YYYY-MM-DD') end AS regdate, b.readcount, m.name 
                    FROM tbl_board b join tbl_member m
                    ON b.id = m.id  	
                    ORDER BY no DESC) A) B
        WHERE 11<=rnum AND rnum<=20;
        

