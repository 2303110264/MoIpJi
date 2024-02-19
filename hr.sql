CREATE TABLE MIJ_member(
  id VARCHAR2(20) PRIMARY KEY
  , password VARCHAR2(300) NOT NULL
  , mail VARCHAR2(60) UNIQUE NOT NULL
  , nickname VARCHAR2(24)
  , gender NUMBER(1)
  , birthday date
  , join_date date DEFAULT sysdate
  , m_type NUMBER(1) NOT NULL
  , salt VARCHAR2(16) NOT NULL
);
-- type = 관리자/회원 분류"			
			
			
CREATE TABLE MIJ_closet(
  c_num NUMBER(3) PRIMARY KEY
  , c_name VARCHAR2(30) NOT NULL
  , c_category NUMBER(1) NOT NULL
  , c_add_id VARCHAR2(20)
  , c_warm_point NUMBER(2) NOT NULL
  , CONSTRAINT c_add_id FOREIGN KEY(c_add_id) REFERENCES MIJ_member(id)
);
			
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
-- f_ncst=실황/예보 구분
-- PTY=강수형태/SKY=하늘/T1H=기온/REH=습도/WSD=풍속

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
);

--drop table MIJ_closet;
--drop table mij_member;
--drop table mij_user_log;