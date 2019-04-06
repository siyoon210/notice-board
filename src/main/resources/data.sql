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

INSERT INTO user (email, password, nick_name)
VALUES ('siyoon210@gmail.com', '$2a$10$PP2QsDhRgXnLdRsR5TpL0.s/DodmN7UAT/1Ono6saTpb.WIJYt4BC', '시윤');
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);

INSERT INTO notice_content (content)
VALUES ('공지사항에 대한 자세한 내용은 이부분에 적어주세요.');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('이곳은 공지사항 게시판입니다.', 1, 1, '2019-04-04 21:18:18.076000', '2019-04-04 21:18:18.076000');

INSERT INTO notice_content (content)
VALUES ('끝까지 힘내서 마무리 하겠습니다!');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('알 서포트 과제 제출일까지 2일 남았습니다.', 2, 2, '2019-04-06 08:18:18.076000', '2019-04-06 08:18:18.076000');

INSERT INTO notice_content (content)
VALUES ('알서포트는 세계 최초로 웹과 아이콘을 이용한 원격 제어 시스템의 특허 기술을 상용화한 이래로 원격 지원, 원격 제어 기술의 세계 표준을 만들어왔습니다. 뛰어난 기술력을 바탕으로 수많은 특허를 획득하였고, 세계 시장에서 지적재산권으로 보호받고 있습니다.
알서포트는 일본 시장 점유율 1위 기업으로 자리 잡았고, 미국과 중국에 법인을 설립해 현지 최고 수준의 업체들과 차례로 협업 관계를 맺으며 글로벌 서비스 기업으로 성장하고 있습니다. 이미 몇 년 전부터 매출의 절반 이상을 해외에서 달성하고 있는 것은 그리 놀라운 사실이 아닙니다.');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('알 서포트의 회사 소개', 3, 2, '2019-04-06 08:24:28.076000', '2019-04-06 08:24:28.076000');

INSERT INTO notice_content (content)
VALUES ('리모트콜은 고객들에게 편안하고 빠른 원격 지원 서비스를 제공하는 기업용 솔루션입니다. 초고속 인터넷 장애 상담에 리모트콜 서비스를 도입한 B사는 연간 270억 원이라는 놀라운 경비 절감 효과를 냈습니다. 이렇게 절감한 비용은 수익 향상, 서비스 품질 개선, 고객을 위한 재투자로 이어집니다.');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('40분을 5분으로 단축시킨 비결 : 리모트콜', 4, 2, '2019-04-06 08:24:28.076000', '2019-04-06 08:24:28.076000');

INSERT INTO notice_content (content)
VALUES ('리모트뷰는 언제 어디서나 내 PC를 제어할 수 있는, 개인/기업용 원격 제어 솔루션입니다.');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('리모트뷰가 드리는 15일의 휴가', 5, 1, '2019-04-06 08:34:28.076000', '2019-04-06 08:34:28.076000');

INSERT INTO notice_content (content)
VALUES ('리모트미팅은 가장 쉽고 빠르게 온라인미팅을 시작할 수 있는 새로운 화상회의 솔루션입니다.');
INSERT INTO notice (title, notice_content_id, user_id, register_date, modify_date)
VALUES ('회의효과 VS 비용절감 : 리모트미팅', 6, 1, '2019-04-06 08:44:28.076000', '2019-04-06 08:44:28.076000');