CREATE TABLE MIJ_member(
  id VARCHAR2(20) PRIMARY KEY
  , password VARCHAR2(300) NOT NULL
  , mail VARCHAR2(60) UNIQUE NOT NULL
  , nickname VARCHAR2(24)
  , gender VARCHAR2(12)
  , birthday date
  , join_date date DEFAULT sysdate
  , m_type NUMBER(1) NOT NULL
  , salt VARCHAR2(40) NOT NULL
);
-- type = ������/ȸ�� �з�"			
			
			
CREATE TABLE MIJ_closet(
  c_num NUMBER(6) PRIMARY KEY
  , c_name VARCHAR2(30) NOT NULL
  , c_category VARCHAR2(20) NOT NULL
  , c_add_id VARCHAR2(20)
  , c_warm_point NUMBER(2) NOT NULL
  , CONSTRAINT c_add_id FOREIGN KEY(c_add_id) REFERENCES MIJ_member(id)
);
-- top, bottom, hat, coat, feet, etc

CREATE SEQUENCE c_num_seq
START WITH 1 -- 시작할 숫자
INCREMENT BY 1 -- 증가할 크기
MINVALUE 1 -- 최소값
MAXVALUE 999999 -- 최대값
CYCLE;
			
CREATE TABLE MIJ_weather(
  day DATE NOT NULL
  , time DATE NOT NULL
  , f_ncst NUMBER(1)
  , PTY NUMBER(1)
  , SKY NUMBER(1)
  , T1H NUMBER
  , REH NUMBER(2)
  , WSD NUMBER
  , CONSTRAINT MIJ_weather_pk PRIMARY KEY(day, time)
);
-- f_ncst=��Ȳ/���� ����
-- PTY=��������/SKY=�ϴ�/T1H=���/REH=����/WSD=ǳ��

CREATE SEQUENCE mij_log_no;
CREATE TABLE MIJ_user_log(
  log_no NUMBER DEFAULT MIJ_log_no.nextval
  , day DATE
  , m_id VARCHAR2(20) NOT NULL
  , top VARCHAR2(30)
  , bottom VARCHAR2(30)
  , hat VARCHAR2(30)
  , coat VARCHAR2(30)
  , feet VARCHAR2(30)
  , etc VARCHAR2(30)
  , CONSTRAINT m_id FOREIGN KEY(m_id) REFERENCES MIJ_member(id)
  ON DELETE CASCADE
);

--drop table MIJ_closet;
--drop table mij_member;
--drop table mij_user_log;

--select * from mij_member;
--delete mij_member where id='daquarter';