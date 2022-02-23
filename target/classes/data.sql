INSERT INTO meeting_room(room_name) VALUES ('A1 Room');
INSERT INTO meeting_room(room_name) VALUES ('A2 Room');
INSERT INTO meeting_room(room_name) VALUES ('A3 Room');
INSERT INTO meeting_room(room_name) VALUES ('A4 Room');
INSERT INTO meeting_room(room_name) VALUES ('A5 Room');
INSERT INTO meeting_room(room_name) VALUES ('A6 Room');
INSERT INTO meeting_room(room_name) VALUES ('A7 Room');

INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE, 1);
INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE - 1, 1);

INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE, 7);
INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE + 1, 7);
INSERT INTO reservable_room(reserved_date,room_id) VALUES (CURRENT_DATE - 1, 7);

INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('chiga','科技','基嘉','demo','USER');
INSERT INTO usr(user_id, first_name, last_name, password, role_name) VALUES ('admon','管理員','系統','demo','ADMIN');
