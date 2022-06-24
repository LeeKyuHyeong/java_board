
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

--SELECT * FROM tbl_board;
SELECT * FROM tbl_member;

INSERT INTO tbl_member(id, name, password, birth)
values('abc', '에이비', '1234', '2000-01-01');

SELECT count(*) as cnt FROM TBL_MEMBER
where id = 'abc';

DELETE FROM tbl_member WHERE id = 'abc';

