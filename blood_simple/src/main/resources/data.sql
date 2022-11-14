-- Adrese

insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Smederevo', 'Srbija', 'a2', 'Scepina ulica', 23.567, 31.235);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Podgorica', 'Crna Gora', '32', 'Narodnih heroja', 52.553, 31.535);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Pazar', 'Srbija', '48', 'Nikole Krge', 13.567, 61.434);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Smederevo', 'Srbija', '63', 'Ivana Gundulica',233.367, 123.735);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Beograd', 'Srbija', '2B', 'Gospodara Vucica', 13.567, 31.235);

insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '8b', 'Puskinova', 11.146, 23.555);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Smederevo', 'Srbija', '3', 'Djure Danicica',233.367, 123.735);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Beograd', 'Srbija', '32', 'Gospodara Vucica', 13.567, 31.235);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '86', 'Bulevar oslobodjenja', 11.146, 23.555);

insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '153', 'Bulevar oslobodjenja', 11.146, 23.555);

insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '147', 'Bulevar oslobodjenja', 11.146, 23.555);


-- Users

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('2310987760011', 'miki@email.com', '123', 'Miki', 'Mikic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER');


-- SysAdmin 

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('2310999760011', 'dule@email.com', 'dule23', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN');

-- MedicalAdmin 

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('1212199760011', 'niki@email.com', 'dule23', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'MEDICAL_ADMIN');

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('743199760011', 'lale@email.com', 'dule23', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN');

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('145299763041', 'ble@email.com', 'dule23', 'Boris', 'Tadic','MALE', 4, '063132156', 'menadzer', 'Unemployed manager ', 'MEDICAL_ADMIN');

-- MedicalCenters

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
values ('Klinicki Centar Lab - KCL', 'Dosta smo dobar centar za vadjenje krvi, za to smo top', 1, '743199760011', 5.0);

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
values ('Blood Drop', 'Super smo!', 2, '1212199760011', 3.0);

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
values ('Blood center', 'Najbolji!', 10, '1212199760011', 3.0);

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
values ('Blood Dropp', 'Super smo!', 11, '1212199760011', 4.0);

-- Medical Staff of MedicalCenters
insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id)
values ( 1, '743199760011');


insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id)
values ( 1, '1212199760011');

--USERS

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('17u665767', 'klmp@email.com', 'klempa23', 'Klempica', 'Klempic','FEMALE', 6, '0641123456', 'student', 'poslednja godina ftn-a', 'USER');

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('34c9760011', 'lepomir@email.com', 'dule23', 'Lepomir', 'Bakic','MALE', 4, '0661453456', 'nezaposlen', '... ', 'USER');

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('7y67679763041', 'cola@email.com', 'dule23', 'Nikola', 'Colic','MALE', 7, '0695632156', 'policajac', 'volim da dajem krv', 'USER');


