-- ======================================================================
-- AUTHORITIES
    insert into ROLE values (1, 'ROLE_SYSTEM_ADMIN');   -- System administrator has this authority
    insert into ROLE values (2, 'ROLE_MEDICAL_ADMIN');  -- Center's admin and Medical staff has this authority
    insert into ROLE values (3, 'ROLE_USER');           -- Registered user has this authority
    insert into ROLE values (4, 'ROLE_COMMON');         -- Every user has this authority
-- ======================================================================
-- ADDRESSES
    -- id=1 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Smederevo', 'Srbija', 'a2', 'Scepina ulica', 23.567, 31.235);
    -- id=2 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Novi Sad', 'Srbija', '32', 'Narodnih heroja', 52.553, 31.535);
    -- id=3 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Novi Pazar', 'Srbija', '48', 'Nikole Krge', 13.567, 61.434);
    -- id=4 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Smederevo', 'Srbija', '63', 'Ivana Gundulica',233.367, 123.735);
    -- id=5 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Beograd', 'Srbija', '2B', 'Gospodara Vucica', 13.567, 31.235);
    -- id=6 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Novi Sad', 'Srbija', '25', 'Mise Dimitrijevica', 11.146, 23.555);
    -- id=7 -- in the database
    insert into addresses (address_city, address_country, address_number, address_street, address_x, address_y)
    values ('Novi Sad', 'Srbija', '3', 'Resavska', 11.146, 23.555);



-- ======================================================================
-- DONATION FORM


-- q13 <50kg
-- q03 healthy
-- q18 alergies, rash
-- q11 temperagure, medicine
-- q25 period(menstrual cycle)
-- q10 tooth
-- q20a q20c operation tattoo
insert into donation_form (donation_form_id, donation_form_date, q13, q03, q11, q25, q10, q20a, q20c, q18, user_id,
 q01, q02, q04,q05,q06,q7,q08,q09,q12,q14,q15,q16,q17,q19,q20b,q21,q22a,q22b,q22c,q22d,q22e,q22f,q22g,
 q23a,q23b,q23c,q23d,q23e,q23f,q24, q26)
values (1, '2023-01-29T08:20:00', false, false, false, false, false,false,false, false, 6,
 false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
 ,false,false,false,false,false,false,false,false,false,false,false,false,false);


insert into donation_form (donation_form_id, donation_form_date, q13, q03, q11, q25, q10, q20a, q20c, q18, user_id,
 q01, q02, q04,q05,q06,q7,q08,q09,q12,q14,q15,q16,q17,q19,q20b,q21,q22a,q22b,q22c,q22d,q22e,q22f,q22g,
 q23a,q23b,q23c,q23d,q23e,q23f,q24, q26)
values (2, '2023-01-29T08:20:00', false, false, false, false, true,false,false, true, 7,
 false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
 ,false,false,false,false,false,false,false,false,false,false,false,false,false);

insert into donation_form (donation_form_id, donation_form_date, q13, q03, q11, q25, q10, q20a, q20c, q18, user_id,
 q01, q02, q04,q05,q06,q7,q08,q09,q12,q14,q15,q16,q17,q19,q20b,q21,q22a,q22b,q22c,q22d,q22e,q22f,q22g,
 q23a,q23b,q23c,q23d,q23e,q23f,q24, q26)
values (3, '2023-01-29T08:20:00', false, false, false, false, false,false,false,false, 8,
 false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
 ,false,false,false,false,false,false,false,false,false,false,false,false,false);

--








-- ======================================================================
-- USERS and USER_ROLE
    -- SystemAdmin
        -- id=1 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('2310999760011', 'dule@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN', true, '', 0, '2023-05-29T08:00:00', false);
            -- user's authority [SYSTEM_ADMIN=1, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (1, 1);
            insert into USER_ROLE(user_id, authority_id) values (1, 4);
    -- CenterAdmins and Medical Staff
        -- id=2 -- in the database -- [CenterAdmin to MedicalCenter1] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('1212199760011', 'niki@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'MEDICAL_ADMIN', true, '', 0, '2023-04-29T08:00:00', true);
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (2, 2);
            insert into USER_ROLE(user_id, authority_id) values (2, 4);
        -- id=3 -- in the database -- [CenterAdmin to MedicalCenter2] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('743199760011', 'lale@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN', true, '', 0, '2023-05-15T09:00:00', false);

--        values ('743199760011', 'lale@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (3, 2);
            insert into USER_ROLE(user_id, authority_id) values (3, 4);
        -- id=4 -- in the database -- [MedicalStaff to MedicalCenter2]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('145299763041', 'ble@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Boris', 'Tadic','MALE', 4, '063132156', 'menadzer', 'Unemployed manager ', 'MEDICAL_ADMIN', true, '', 0, '2023-05-25T10:00:00', false);
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (4, 2);
            insert into USER_ROLE(user_id, authority_id) values (4, 4);
        -- id=5 -- in the database -- [MedicalStaff to MedicalCenter3]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('145299763044', 'tijana@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Tijana', 'Tijanic','FEMALE', 4, '06313215654', 'med.sestra', 'Med.sestra', 'MEDICAL_ADMIN', true, '', 0, '2023-05-30T08:00:00', false);
        -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
        insert into USER_ROLE(user_id, authority_id) values (5, 2);
        insert into USER_ROLE(user_id, authority_id) values (5, 4);
    -- RegisteredUsers
        -- id=6 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('2310987760011', 'miki@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Miki', 'Mikic','MALE', 3, '0621123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '', 1, '2023-05-29T11:00:00', false);
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (6, 3);
            insert into USER_ROLE(user_id, authority_id) values (6, 4);
        -- id=7 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('35445421760011', 'jankovicmaraja99@gmail.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Marija', 'Jankovic','FEMALE', 6, '0631123333', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '', 0, '2023-05-11T08:00:00', false);
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (7, 3);
            insert into USER_ROLE(user_id, authority_id) values (7, 4);
        -- id=8 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code, negative_points, last_blood_donation, first_login)
        values ('35445421760022', 'sima@email.com', '$2a$10$2Zq8hqDWclD1Up4Ho9e0jeky19W8cZTbHTvFNa3rxQKGO7K1RX5pG', 'Sima', 'Simic','MALE', 7, '0691123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '', 0, '2023-05-29T08:00:00', false);
        -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
        insert into USER_ROLE(user_id, authority_id) values (8, 3);
        insert into USER_ROLE(user_id, authority_id) values (8, 4);
-- ======================================================================
-- MEDICAL CENTERS -- with CenterAdmins and MedicalStaff
        -- id=1 -- in the database
        insert into medical_centers (center_name, center_description, center_address_id, grade)
        values ('Blood Drop', 'Vadimo krv kap po kap, kisa sprema se... ok prestacu', 2,  3.0);
            -- Medical Staff in the Medical Center1
            insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id) values (1, 2);
            insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id) values (1, 3);
        -- id=2 -- in the database
        insert into medical_centers (center_name, center_description, center_address_id,  grade)
        values ('Klinicki Centar Lab - KCL', 'Dosta smo dobar centar za vadjenje krvi, za to smo top', 1, 5.0);
            -- Medical Staff in the Medical Center2
            insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id) values (2, 4);
        -- id=3 -- in the database
        insert into medical_centers (center_name, center_description, center_address_id,  grade)
        values ('Medicinski centar Zdravlje', 'Najbolji u gradu!', 7, 5.0);
        -- Medical Staff in the Medical Center3
        insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id) values (3, 5);
-- ======================================================================
-- APPOINTMENTS


insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status, user_user_id) values

        -- Center 01 -
        (30, '2023-05-29T08:00:00', 1, true, 'finished', 6 ), (30, '2023-06-11T09:20:00', 1, true, 'finished', 7), (30, '2023-05-27T08:40:00', 1, true, 'finished', 8),
        (30, '2023-06-11T09:00:00', 1, true, 'missed', 6), (30, '2023-05-29T10:20:00', 1, true, 'unfulfilled_conditions', 7), (15, '2023-04-21T09:40:00', 1, true, 'unfulfilled_conditions', 8);

insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status, user_user_id) values

        -- Center 01 -
        (30, '2023-04-29T08:00:00', 1, true, 'finished', 6 ), (10, '2023-06-15T08:20:00', 1, true, 'taken', 6), (30, '2023-06-19T08:40:00', 1, true, 'taken', 7),
        (30, '2023-06-20T09:00:00', 1, true, 'taken', 8);

insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status) values
(30, '2023-06-11T09:00:00', 1, false, 'free');






-- Center 02 -
insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status) values
        (30, '2023-06-29T08:00:00', 2, false, 'free'), (10, '2023-06-11T08:20:00', 2, false, 'free');


insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status, user_user_id) values

        (30, '2023-04-29T09:00:00', 2, true, 'finished', 6), (30, '2023-01-29T09:20:00', 2, true, 'missed', 6), (30, '2023-06-14T09:40:00', 2, true, 'taken', 7);


        -- Center 03 -

insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status) values
(30, '2023-06-15T08:00:00', 3, false, 'free'), (30, '2023-06-17T08:20:00', 3, false, 'free');

insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved, status, user_user_id) values

        (30, '2023-03-25T08:00:00', 3, false, 'finished', 6), (30, '2023-03-29T08:20:00', 3, false, 'missed', 7), (30, '2023-06-19T08:40:00', 3, false, 'taken', 8);
-- ======================================================================
-- BLOOD STORE

    insert into blood_storages(storage_id, storage_ap, storage_an, storage_abp, storage_abn,
     storage_bp, storage_bn, storage_op, storage_on, medical_center_center_id)
        values(1, 10000,10000,8000,8000,11000,11000,9000,9000, 1);
    insert into blood_storages(storage_id, storage_ap, storage_an, storage_abp, storage_abn,
         storage_bp, storage_bn, storage_op, storage_on, medical_center_center_id)
            values(2, 8000,8000,7000,7000,6000,6000,5000,5000, 2);
    insert into blood_storages(storage_id, storage_ap, storage_an, storage_abp, storage_abn,
             storage_bp, storage_bn, storage_op, storage_on, medical_center_center_id)
                values(3, 4000,5000,6000,7000,6000,6000,5000,5000, 3);

-- ======================================================================
-- EQUIPMENT

insert into equipment_storages(equipment_id, medical_center_center_id, sbag150, sbag400, sbag600,
    dbag150, dbag400, dbag600, tbag150, tbag400, tbag600, blood_lancet)
    values(1, 1, 10, 11, 12, 7, 8, 9, 2, 3, 4, 5);

insert into equipment_storages(equipment_id, medical_center_center_id, sbag150, sbag400, sbag600,
    dbag150, dbag400, dbag600, tbag150, tbag400, tbag600, blood_lancet)
    values(2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 7);

insert into equipment_storages(equipment_id, medical_center_center_id, sbag150, sbag400, sbag600,
        dbag150, dbag400, dbag600, tbag150, tbag400, tbag600, blood_lancet)
        values(3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7);

-- ======================================================================
-- REPORT
insert into report_storages(blood_amount, appointment_appointment_id)
    values(300, 1);
insert into report_storages(blood_amount, appointment_appointment_id)
    values(400, 2);
insert into report_storages(blood_amount, appointment_appointment_id)
    values(500, 3);
insert into report_storages(blood_amount, appointment_appointment_id)
    values(300, 7);
insert into report_storages(blood_amount, appointment_appointment_id)
    values(300, 14);
insert into report_storages(blood_amount, appointment_appointment_id)
    values(300, 19);

-- ======================================================================
---- APPOINTMENTS MEDICAL STAFF
insert into appointments_medical_staff (appointment_appointment_id, medical_staff_user_id )
 values (1 , 2),  (2 , 3),  (3 , 2),  (3 , 3),  (4 , 2),  (5, 3),  (6, 3),
 (7, 2), (8, 3), (9, 3), (10, 3), (10, 2), (11, 3);


insert into appointments_medical_staff (appointment_appointment_id, medical_staff_user_id )
  values (12 , 4),  (13 , 4),  (14 , 4),  (15 , 4),  (16 , 4);


insert into appointments_medical_staff (appointment_appointment_id, medical_staff_user_id )
  values (17 , 5),  (18 , 5),  (19 , 5),  (20 , 5),  (21 , 5);