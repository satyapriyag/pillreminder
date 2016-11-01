INSERT INTO category(category_id,category_name,deleted) VALUES (1,'test', null);

INSERT INTO pill(pill_id, pill_name, pill_category_id, deleted) VALUES (1,'test', 1, null);

INSERT INTO role(role_id, role_name) VALUES(1,'admin');
INSERT INTO role(role_id, role_name) VALUES(2,'user');

INSERT INTO user(user_id, user_name, user_email, user_contact, deleted) VALUES (1,'test','test@test.com','99999999999', null);

INSERT INTO pill(pill_id, pill_name, pill_category_id, deleted) VALUES (2,'test', 1, null);
INSERT INTO pill(pill_id, pill_name, pill_category_id, deleted) VALUES (3,'test', 1, null);
INSERT INTO alternative(alt_id, pill_id, alternate_pill_id, deleted) VALUES (1, 2, 3, null);

INSERT INTO pill(pill_id, pill_name, pill_category_id, deleted) VALUES (4,'test', 1, null);
INSERT INTO user(user_id, user_name, user_email, user_contact, deleted) VALUES (2,'test','test2@test.com','99999999999', null);
INSERT INTO user(user_id, user_name, user_email, user_contact, deleted) VALUES (3,'test','test3@test.com','99999999999', null);
INSERT INTO alarm(a_id, a_end_date, a_recurrence, a_start_date, deleted, a_pill_id, a_user_id) VALUES (1,'2016-10-05', 3, '2018-10-13', null, 4, 2);
INSERT INTO alarm(a_id, a_end_date, a_recurrence, a_start_date, deleted, a_pill_id, a_user_id) VALUES (10,'2016-10-05', 3, '2018-10-13', null, 4, 2);