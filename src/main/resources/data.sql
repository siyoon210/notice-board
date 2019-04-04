INSERT INTO role (authority)
VALUES ('ADMIN');
INSERT INTO role (authority)
VALUES ('USER');

INSERT INTO user (email, password, nick_name)
VALUES ('admin@notice.com', '$2a$10$PP2QsDhRgXnLdRsR5TpL0.s/DodmN7UAT/1Ono6saTpb.WIJYt4BC', '관리자');
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (1, 2);

INSERT INTO notice_content (content)
VALUES ('공지사항에 대한 자세한 내용은 이부분에 적어주세요.');

INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('이곳은 공지사항 게시판입니다.', 1, 1, '2019-04-04 21:18:18.076000', '2019-04-04 21:18:18.076000');
