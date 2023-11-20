DROP TABLE album_info;
DROP TABLE music_info;
DROP TABLE playlist_info;
DROP TABLE user_info;

CREATE TABLE user_info(
    user_id VARCHAR2(50) PRIMARY KEY,
    user_name VARCHAR2(20),
    user_nickname VARCHAR2(50),
    user_password VARCHAR2(100),
    email VARCHAR2(100),
    phone_number VARCHAR2(11),
    profile_img BLOB
);

CREATE TABLE follow_list(
    follower_id VARCHAR2(50),
    following_id VARCHAR2(50)
);

CREATE TABLE music_info(
    song_id NUMBER PRIMARY KEY, --½ÃÄö½º & Æ®¸®°Å·Î ÀÚµ¿ Ãß°¡
    song_name VARCHAR2(100),
    aritst_name VARCHAR2(50),
    genre VARCHAR2(50),
    album_img BLOB
);

CREATE TABLE playlist_info(
    playlist_id NUMBER PRIMARY KEY, --½ÃÄö½º & Æ®¸®°Å·Î ÀÚµ¿ Ãß°¡
    user_id VARCHAR2(50),
    playlist_name VARCHAR2(100),
    image BLOB
);

DROP TABLE playlist_song;
CREATE TABLE playlist_song(
    playlist_id NUMBER,
    song_id NUMBER
);


CREATE SEQUENCE playlist_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;
  
CREATE OR REPLACE TRIGGER playlist_trigger
BEFORE INSERT ON playlist_song
FOR EACH ROW
BEGIN
    SELECT playlist_seq.NEXTVAL
    INTO:NEW.playlist_id
    FROM dual;
END;

CREATE SEQUENCE music_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;
  
CREATE OR REPLACE TRIGGER music_trigger
BEFORE INSERT ON music_info
FOR EACH ROW
BEGIN
    SELECT music_seq.NEXTVAL
    INTO:NEW.song_id
    FROM dual;
END;

-----------------------

SET DEFINE OFF;

INSERT INTO music_info(song_name, artist_name, genre)
SELECT 'Love Lee', 'AKMU(¾Ç¹Â)', '´í½º' FROM DUAL UNION ALL
SELECT 'ÈÄ¶óÀÌÀÇ ²Þ', 'AKMU(¾Ç¹Â)', 'Æ÷Å©/ºí·ç½º' FROM DUAL UNION ALL

SELECT '³Í °¨µ¿ÀÌ¾ú¾î', '¼º½Ã°æ', '¹ß¶óµå' FROM DUAL UNION ALL
SELECT 'ÈñÀç', '¼º½Ã°æ', '±¹È­²É Çâ±â OST' FROM DUAL UNION ALL
SELECT '°Å¸®¿¡¼­', '¼º½Ã°æ', '¹ß¶óµå' FROM DUAL UNION ALL
SELECT 'I Love U', '¼º½Ã°æ', '¹ß¶óµå' FROM DUAL UNION ALL
SELECT '³»°Ô ¿À´Â±æ', '¼º½Ã°æ', '¹ß¶óµå' FROM DUAL UNION ALL

SELECT 'Golden','Harry Styles','ÆË(pop)' FROM DUAL UNION ALL
SELECT 'My Eyes','Travis Scott', 'ÈüÇÕ(hiphop)' FROM DUAL UNION ALL
SELECT 'Baggy Jeans','NCT U','ÄÉÀÌÆË(Kpop)' FROM DUAL UNION ALL
SELECT 'Magnolia','Playboi Carti', 'ÈüÇÕ(hiphop)' FROM DUAL UNION ALL
SELECT 'Kiss it Better', 'Rihanna', 'ÆË(pop)' FROM DUAL;

INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('All I Want for Christmas Is You', 'Mariah Carey', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Silent Night', 'Mariah Carey', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Joy to the World', 'Mariah Carey', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Santa Claus Is Comin to Town', 'Mariah Carey', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Miss You Most', 'Mariah Carey', 'ÆË(pop)');

INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('all-american bitch', 'Olivia Rodrigo', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('vampire', 'Olivia Rodrigo', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('get him back!', 'Olivia Rodrigo', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('love is embrassing', 'Olivia Rodrigo', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('teenage dream', 'Olivia Rodrigo', 'ÆË(pop)');

INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('New Jeans', '´ºÁø½º', 'ÄÉÀÌÆË(Kpop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Super Shy', '´ºÁø½º', 'ÄÉÀÌÆË(Kpop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('ETA', '´ºÁø½º', 'ÄÉÀÌÆË(Kpop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Cool with you', '´ºÁø½º', 'ÄÉÀÌÆË(Kpop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Get up', '´ºÁø½º', 'ÄÉÀÌÆË(Kpop)');

INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('¼¶À¸·Î °¡¿ä', 'Ä«´õ°¡µç', '·Ï(Rock)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('±×Àú ±×·± ³¯', 'Ä«´õ°¡µç', '·Ï(Rock)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Lost 2', 'Ä«´õ°¡µç', '·Ï(Rock)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Home Sweet Home', 'Ä«´õ°¡µç', '·Ï(Rock)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('ÀþÀº ²Þ', 'Ä«´õ°¡µç', '·Ï(Rock)');

INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Welcome To New York', 'Taylor Swift', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Blank Space', 'Taylor Swift', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Style', 'Taylor Swift', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Out Of The Woods', 'Taylor Swift', 'ÆË(pop)');
INSERT INTO music_info (song_name, artist_name, genre)
VALUES ('Wildest Dreams', 'Taylor Swift', 'ÆË(pop)');



