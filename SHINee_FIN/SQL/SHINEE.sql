CREATE TABLE album_info(
    album_id VARCHAR2(5) PRIMARY KEY NOT NULL,
    album_name VARCHAR2(100) NOT NULL,
    album_type VARCHAR2(50),
    artist_name VARCHAR2(50) NOT NULL,
    release_date DATE NOT NULL
);

CREATE TABLE music_info(
    song_id VARCHAR2(10) PRIMARY KEY,
    album_id VARCHAR2(5) NOT NULL,
    song_name VARCHAR2(100) NOT NULL,
    artist_name VARCHAR2(50) NOT NULL,
    genre VARCHAR2(50),
    
    CONSTRAINT fk_album_id FOREIGN KEY(album_id) REFERENCES album_info(album_id) 
);

CREATE TABLE playlist_info(
    playlist_id VARCHAR2(20) PRIMARY KEY,
    user_id VARCHAR2(12) NOT NULL,
    playlist_name VARCHAR2(50) NOT NULL,
    create_date DATE NOT NULL,
    
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES user_info(user_id) 
);

--user_info INSERT
INSERT INTO user_info(user_id, user_name, user_nickname, user_password, email, phone_number)
SELECT 'im2ho', '������', 'ĭ��', 'kh123456!', 'kankan@kakao.com', '01037134606' FROM DUAL UNION ALL
SELECT 'tmddo', '�Ƚ¾�', '�ȷ���', 'kh654321!', 'tmddotmddo@naver.com', '01023509928' FROM DUAL UNION ALL
SELECT 'gkdms2710', '������', '�ϸ���', 'kh2710!!', 'gkdmsdl@kakao.com', '01094723499' FROM DUAL UNION ALL
SELECT 'tjrhkd', '������', '�ǻ���', 'kh960006278!@', 'tjrhkddnjs@naver.com', '01025309835' FROM DUAL;

--album_info INSERT
INSERT INTO album_info(album_id, album_name, album_type, artist_name, release_date)
SELECT '01', 'tellusboutyourself', '����', '�鿹��', TO_DATE('2020-12-10') FROM DUAL UNION ALL
SELECT '02', 'THE SHINee World', '����', 'SHINee', TO_DATE('2008-08-28') FROM DUAL UNION ALL
SELECT '03', 'YOU MAKE MY DAY', 'EP', '����ƾ', TO_DATE('2018-07-16') FROM DUAL UNION ALL
SELECT '04', 'Birds Of Prey : The Album', '����Ʈ��', 'various artists', TO_DATE('2020-02-07') FROM DUAL UNION ALL
SELECT '05', 'Love Lee', '�̱�', 'AKMU(�ǹ�)', TO_DATE('2023-08-21') FROM DUAL UNION ALL
SELECT '06', 'UTOPIA', '����', 'Travis Scott', TO_DATE('2023-07-27') FROM DUAL UNION ALL
SELECT '07', 'Born to Die', '����', 'Lana Del Rey', TO_DATE('2010-12-31') FROM DUAL;

--music_info INSERT
SET DEFINE OFF;
INSERT INTO music_info(album_id, song_id, song_name, artist_name, genre)
SELECT '05', '05-1', 'Love Lee', 'AKMU(�ǹ�)', '��' FROM DUAL UNION ALL
SELECT '05', '05-2', '�Ķ����� ��', 'AKMU(�ǹ�)', '��ũ' FROM DUAL UNION ALL
SELECT '04', '04-1', 'Boss Bitch', 'Doja Cat', '����' FROM DUAL UNION ALL
SELECT '04', '04-11', 'Feeling Godd', 'Sofi Tukker', '����' FROM DUAL UNION ALL
SELECT '04', '04-14', 'Im Gonna Love You Just A Little More Baby', 'Summer Walker', 'R&B' FROM DUAL UNION ALL
SELECT '04', '04-4', 'Sway With Me', 'Saweetie, GALXARA', '����' FROM DUAL UNION ALL
SELECT '04', '04-3', 'Diamonds', 'Megan Thee Stallion, Normani', '����' FROM DUAL UNION ALL
SELECT '03', '03-1', '��¼��', '����ƾ', '��' FROM DUAL UNION ALL
SELECT '03', '03-2', 'Holiday', '����ƾ', '��' FROM DUAL UNION ALL
SELECT '02', '02-3', '��Ұ��� ��', 'SHINee', '��' FROM DUAL UNION ALL
SELECT '02', '02-9', '����', 'SHINee', '�߶��' FROM DUAL UNION ALL
SELECT '02', '02-12', '���� �ʹ� ����', 'SHINee', '��' FROM DUAL UNION ALL
SELECT '02', '02-2', '����� ��', 'SHINee', '��' FROM DUAL UNION ALL
SELECT '02', '02-11', 'In My Room', 'SHINee', '�߶��' FROM DUAL UNION ALL
SELECT '01', '01-1', 'Lovegame', '�鿹��', 'R&B' FROM DUAL UNION ALL
SELECT '01', '01-3', 'I am not your ocean anymore', '�鿹��', 'R&B' FROM DUAL UNION ALL
SELECT '01', '01-10', 'Hate you', '�鿹��', 'R&B' FROM DUAL UNION ALL
SELECT '01', '01-11', '0415', '�鿹��', 'R&B' FROM DUAL UNION ALL
SELECT '01', '01-13', 'Im in love', '�鿹��', 'R&B' FROM DUAL UNION ALL
SELECT '06', '06-01', 'MY EYES', 'Travis Scott', '' FROM DUAL UNION ALL
SELECT '06', '06-02', 'LOOOVE', 'Travis Scott', '' FROM DUAL UNION ALL
SELECT '07', '07-01', 'American', 'Lana Del Rey', '' FROM DUAL UNION ALL
SELECT '07', '07-02', 'Summertime Sadness', 'Lana Del Rey', '' FROM DUAL;
SET DEFINE ON;