-- Adrese

insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Smederevo', 'Srbija', 'a2', 'Scepina ulica', 23.567, 31.235);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '32', 'Narodnih heroja', 52.553, 31.535);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Pazar', 'Srbija', '48', 'Nikole Krge', 13.567, 61.434);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Smederevo', 'Srbija', '63', 'Ivana Gundulica',233.367, 123.735);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Beograd', 'Srbija', '2B', 'Gospodara Vucica', 13.567, 31.235);


insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
values ('Novi Sad', 'Srbija', '25', 'Mise Dimitrijevica', 11.146, 23.555);

-- SysAdmin 

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('2310999760011', 'dule@email.com', 'dule23', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN');

-- MedicalAdmin 

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('1212199760011', 'niki@email.com', 'dule23', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'SYSTEM_ADMIN');

insert into users (user_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
values ('743199760011', 'lale@email.com', 'dule23', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'SYSTEM_ADMIN');

-- MedicalCenters

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id)
values ('Klinicki Centar Lab - KCL', 'Dosta smo dobar centar za vadjenje krvi, za to smo top', 1, '743199760011');

insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id)
values ('Blood Drop', 'Vadimo krv kap po kap, kisa sprema se... ok prestacu', 2, '1212199760011');



