INSERT INTO user (email, password, nick_name)
VALUES ('user@naver.com', '1234', '김유저');

INSERT INTO notice_content (content)
VALUES ('공지사항에 대한 자세한 내용은 이부분에 적어주세요.');

INSERT INTO notice (title, notice_content_id, user_id)
VALUES ('이곳은 공지사항 게시판입니다.', 1, 1);
