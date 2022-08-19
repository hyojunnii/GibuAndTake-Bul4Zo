--------------------------------
-- MANAGER
--------------------------------
DROP TABLE Manager CASCADE CONSTRAINTS;

CREATE TABLE Manager (
	"MN_NO"	NUMBER		        NOT NULL,
	"MN_ID"	VARCHAR2(50)		NOT NULL,
	"MN_PWD"	VARCHAR2(50)	NOT NULL,
	"MN_NICK"	VARCHAR2(100)	NOT NULL
);


ALTER TABLE Manager ADD CONSTRAINT "PK_MANAGER" PRIMARY KEY (
	"MN_NO"
);


DROP SEQUENCE SEQ_Manager_NO;
CREATE SEQUENCE SEQ_Manager_NO NOCACHE NOCYCLE;

INSERT INTO Manager(MN_NO, MN_ID, MN_PWD, MN_NICK) VALUES (SEQ_MANAGER_NO.NEXTVAL, 'admin', '1234', '������');
COMMIT;
SELECT * FROM Manager;
