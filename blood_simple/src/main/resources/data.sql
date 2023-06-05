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
-- USERS and USER_ROLE
    -- SystemAdmin
        -- id=1 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('2310999760011', 'dule@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN', true, '');
            -- user's authority [SYSTEM_ADMIN=1, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (1, 1);
            insert into USER_ROLE(user_id, authority_id) values (1, 4);
    -- CenterAdmins and Medical Staff
        -- id=2 -- in the database -- [CenterAdmin to MedicalCenter1] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('1212199760011', 'niki@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'MEDICAL_ADMIN', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (2, 2);
            insert into USER_ROLE(user_id, authority_id) values (2, 4);
        -- id=3 -- in the database -- [CenterAdmin to MedicalCenter2] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('743199760011', 'lale@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (3, 2);
            insert into USER_ROLE(user_id, authority_id) values (3, 4);
        -- id=4 -- in the database -- [MedicalStaff to MedicalCenter2]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('145299763041', 'ble@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Boris', 'Tadic','MALE', 4, '063132156', 'menadzer', 'Unemployed manager ', 'MEDICAL_ADMIN', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (4, 2);
            insert into USER_ROLE(user_id, authority_id) values (4, 4);
        -- id=5 -- in the database -- [MedicalStaff to MedicalCenter3]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('145299763044', 'tijana@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Tijana', 'Tijanic','FEMALE', 4, '06313215654', 'med.sestra', 'Med.sestra', 'MEDICAL_ADMIN', true, '');
        -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
        insert into USER_ROLE(user_id, authority_id) values (5, 2);
        insert into USER_ROLE(user_id, authority_id) values (5, 4);
    -- RegisteredUsers
        -- id=6 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('2310987760011', 'miki@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Miki', 'Mikic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (6, 3);
            insert into USER_ROLE(user_id, authority_id) values (6, 4);
        -- id=7 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('35445421760011', 'jankovicmaraja99@gmail.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Marija', 'Jankovic','FEMALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (7, 3);
            insert into USER_ROLE(user_id, authority_id) values (7, 4);
        -- id=8 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role, is_activated, verification_code)
        values ('35445421760022', 'sima@email.com', '$2a$10$/ZVwaiu9wxLt.UcwpLn21OrXwLC.y6ykH8bpIEMfVdFcB0Rxfb.LK', 'Sima', 'Simic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER', true, '');
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
-- APPOINTMENTS (What Marija added)
insert into appointments(appointment_duration, appointment_start, medical_center_center_id, reserved) values
    -- 2023 - 01 - 29
        -- Center 01 -
        (30, '2023-01-29T08:00:00', 1, false), (30, '2023-01-29T08:20:00', 1, false), (30, '2023-01-29T08:40:00', 1, false),
        (30, '2023-01-29T09:00:00', 1, false), (30, '2023-01-29T09:20:00', 1, false), (30, '2023-01-29T09:40:00', 1, false),
        (30, '2023-01-29T10:00:00', 1, false), (30, '2023-01-29T10:20:00', 1, false), (30, '2023-01-29T10:40:00', 1, false),
        (30, '2023-01-29T11:00:00', 1, false), (30, '2023-01-29T11:20:00', 1, false), (30, '2023-01-29T11:40:00', 1, false),
        (30, '2023-01-29T12:00:00', 1, false), (30, '2023-01-29T12:20:00', 1, false), (30, '2023-01-29T12:40:00', 1, false),
        (30, '2023-01-29T13:00:00', 1, false), (30, '2023-01-29T13:20:00', 1, false), (30, '2023-01-29T13:40:00', 1, false),
        (30, '2023-01-29T14:00:00', 1, false), (30, '2023-01-29T14:20:00', 1, false), (30, '2023-01-29T14:40:00', 1, false),
        (30, '2023-01-29T15:00:00', 1, false), (30, '2023-01-29T15:20:00', 1, false), (30, '2023-01-29T15:40:00', 1, false),
        (30, '2023-01-29T16:00:00', 1, false), (30, '2023-01-29T16:20:00', 1, false), (30, '2023-01-29T16:40:00', 1, false),
        -- Center 02 -
        (30, '2023-01-29T08:00:00', 2, false), (30, '2023-01-29T08:20:00', 2, false), (30, '2023-01-29T08:40:00', 2, false),
        (30, '2023-01-29T09:00:00', 2, false), (30, '2023-01-29T09:20:00', 2, false), (30, '2023-01-29T09:40:00', 2, false),
        (30, '2023-01-29T10:00:00', 2, false), (30, '2023-01-29T10:20:00', 2, false), (30, '2023-01-29T10:40:00', 2, false),
        (30, '2023-01-29T11:00:00', 2, false), (30, '2023-01-29T11:20:00', 2, false), (30, '2023-01-29T11:40:00', 2, false),
        (30, '2023-01-29T12:00:00', 2, false), (30, '2023-01-29T12:20:00', 2, false), (30, '2023-01-29T12:40:00', 2, false),
        (30, '2023-01-29T13:00:00', 2, false), (30, '2023-01-29T13:20:00', 2, false), (30, '2023-01-29T13:40:00', 2, false),
        (30, '2023-01-29T14:00:00', 2, false), (30, '2023-01-29T14:20:00', 2, false), (30, '2023-01-29T14:40:00', 2, false),
        (30, '2023-01-29T15:00:00', 2, false), (30, '2023-01-29T15:20:00', 2, false), (30, '2023-01-29T15:40:00', 2, false),
        (30, '2023-01-29T16:00:00', 2, false), (30, '2023-01-29T16:20:00', 2, false), (30, '2023-01-29T16:40:00', 2, false),
        -- Center 03 -
        (30, '2023-01-29T08:00:00', 3, false), (30, '2023-01-29T08:20:00', 3, false), (30, '2023-01-29T08:40:00', 3, false),
        (30, '2023-01-29T09:00:00', 3, false), (30, '2023-01-29T09:20:00', 3, false), (30, '2023-01-29T09:40:00', 3, false),
        (30, '2023-01-29T10:00:00', 3, false), (30, '2023-01-29T10:20:00', 3, false), (30, '2023-01-29T10:40:00', 3, false),
        (30, '2023-01-29T11:00:00', 3, false), (30, '2023-01-29T11:20:00', 3, false), (30, '2023-01-29T11:40:00', 3, false),
        (30, '2023-01-29T12:00:00', 3, false), (30, '2023-01-29T12:20:00', 3, false), (30, '2023-01-29T12:40:00', 3, false),
        (30, '2023-01-29T13:00:00', 3, false), (30, '2023-01-29T13:20:00', 3, false), (30, '2023-01-29T13:40:00', 3, false),
        (30, '2023-01-29T14:00:00', 3, false), (30, '2023-01-29T14:20:00', 3, false), (30, '2023-01-29T14:40:00', 3, false),
        (30, '2023-01-29T15:00:00', 3, false), (30, '2023-01-29T15:20:00', 3, false), (30, '2023-01-29T15:40:00', 3, false),
        (30, '2023-01-29T16:00:00', 3, false), (30, '2023-01-29T16:20:00', 3, false), (30, '2023-01-29T16:40:00', 3, false),
               
    -- 2023 - 01 - 30
        -- Center 01 -
        (30, '2023-01-30T08:00:00', 1, false), (30, '2023-01-30T08:20:00', 1, false), (30, '2023-01-30T08:40:00', 1, false),
        (30, '2023-01-30T09:00:00', 1, false), (30, '2023-01-30T09:20:00', 1, false), (30, '2023-01-30T09:40:00', 1, false),
        (30, '2023-01-30T10:00:00', 1, false), (30, '2023-01-30T10:20:00', 1, false), (30, '2023-01-30T10:40:00', 1, false),
        (30, '2023-01-30T11:00:00', 1, false), (30, '2023-01-30T11:20:00', 1, false), (30, '2023-01-30T11:40:00', 1, false),
        (30, '2023-01-30T12:00:00', 1, false), (30, '2023-01-30T12:20:00', 1, false), (30, '2023-01-30T12:40:00', 1, false),
        (30, '2023-01-30T13:00:00', 1, false), (30, '2023-01-30T13:20:00', 1, false), (30, '2023-01-30T13:40:00', 1, false),
        (30, '2023-01-30T14:00:00', 1, false), (30, '2023-01-30T14:20:00', 1, false), (30, '2023-01-30T14:40:00', 1, false),
        (30, '2023-01-30T15:00:00', 1, false), (30, '2023-01-30T15:20:00', 1, false), (30, '2023-01-30T15:40:00', 1, false),
        (30, '2023-01-30T16:00:00', 1, false), (30, '2023-01-30T16:20:00', 1, false), (30, '2023-01-30T16:40:00', 1, false),
        -- Center 02 -
        (30, '2023-01-30T08:00:00', 2, false), (30, '2023-01-30T08:20:00', 2, false), (30, '2023-01-30T08:40:00', 2, false),
        (30, '2023-01-30T09:00:00', 2, false), (30, '2023-01-30T09:20:00', 2, false), (30, '2023-01-30T09:40:00', 2, false),
        (30, '2023-01-30T10:00:00', 2, false), (30, '2023-01-30T10:20:00', 2, false), (30, '2023-01-30T10:40:00', 2, false),
        (30, '2023-01-30T11:00:00', 2, false), (30, '2023-01-30T11:20:00', 2, false), (30, '2023-01-30T11:40:00', 2, false),
        (30, '2023-01-30T12:00:00', 2, false), (30, '2023-01-30T12:20:00', 2, false), (30, '2023-01-30T12:40:00', 2, false),
        (30, '2023-01-30T13:00:00', 2, false), (30, '2023-01-30T13:20:00', 2, false), (30, '2023-01-30T13:40:00', 2, false),
        (30, '2023-01-30T14:00:00', 2, false), (30, '2023-01-30T14:20:00', 2, false), (30, '2023-01-30T14:40:00', 2, false),
        (30, '2023-01-30T15:00:00', 2, false), (30, '2023-01-30T15:20:00', 2, false), (30, '2023-01-30T15:40:00', 2, false),
        (30, '2023-01-30T16:00:00', 2, false), (30, '2023-01-30T16:20:00', 2, false), (30, '2023-01-30T16:40:00', 2, false),
        -- Center 03 -
        (30, '2023-01-30T08:00:00', 3, false), (30, '2023-01-30T08:20:00', 3, false), (30, '2023-01-30T08:40:00', 3, false),
        (30, '2023-01-30T09:00:00', 3, false), (30, '2023-01-30T09:20:00', 3, false), (30, '2023-01-30T09:40:00', 3, false),
        (30, '2023-01-30T10:00:00', 3, false), (30, '2023-01-30T10:20:00', 3, false), (30, '2023-01-30T10:40:00', 3, false),
        (30, '2023-01-30T11:00:00', 3, false), (30, '2023-01-30T11:20:00', 3, false), (30, '2023-01-30T11:40:00', 3, false),
        (30, '2023-01-30T12:00:00', 3, false), (30, '2023-01-30T12:20:00', 3, false), (30, '2023-01-30T12:40:00', 3, false),
        (30, '2023-01-30T13:00:00', 3, false), (30, '2023-01-30T13:20:00', 3, false), (30, '2023-01-30T13:40:00', 3, false),
        (30, '2023-01-30T14:00:00', 3, false), (30, '2023-01-30T14:20:00', 3, false), (30, '2023-01-30T14:40:00', 3, false),
        (30, '2023-01-30T15:00:00', 3, false), (30, '2023-01-30T15:20:00', 3, false), (30, '2023-01-30T15:40:00', 3, false),
        (30, '2023-01-30T16:00:00', 3, false), (30, '2023-01-30T16:20:00', 3, false), (30, '2023-01-30T16:40:00', 3, false),
     
    -- 2023 - 01 - 31
         -- Center 01 -
         (30, '2023-01-31T08:00:00', 1, false), (30, '2023-01-31T08:20:00', 1, false), (30, '2023-01-31T08:40:00', 1, false),
         (30, '2023-01-31T09:00:00', 1, false), (30, '2023-01-31T09:20:00', 1, false), (30, '2023-01-31T09:40:00', 1, false),
         (30, '2023-01-31T10:00:00', 1, false), (30, '2023-01-31T10:20:00', 1, false), (30, '2023-01-31T10:40:00', 1, false),
         (30, '2023-01-31T11:00:00', 1, false), (30, '2023-01-31T11:20:00', 1, false), (30, '2023-01-31T11:40:00', 1, false),
         (30, '2023-01-31T12:00:00', 1, false), (30, '2023-01-31T12:20:00', 1, false), (30, '2023-01-31T12:40:00', 1, false),
         (30, '2023-01-31T13:00:00', 1, false), (30, '2023-01-31T13:20:00', 1, false), (30, '2023-01-31T13:40:00', 1, false),
         (30, '2023-01-31T14:00:00', 1, false), (30, '2023-01-31T14:20:00', 1, false), (30, '2023-01-31T14:40:00', 1, false),
         (30, '2023-01-31T15:00:00', 1, false), (30, '2023-01-31T15:20:00', 1, false), (30, '2023-01-31T15:40:00', 1, false),
         (30, '2023-01-31T16:00:00', 1, false), (30, '2023-01-31T16:20:00', 1, false), (30, '2023-01-31T16:40:00', 1, false),
         -- Center 02 -
         (30, '2023-01-31T08:00:00', 2, false), (30, '2023-01-31T08:20:00', 2, false), (30, '2023-01-31T08:40:00', 2, false),
         (30, '2023-01-31T09:00:00', 2, false), (30, '2023-01-31T09:20:00', 2, false), (30, '2023-01-31T09:40:00', 2, false),
         (30, '2023-01-31T10:00:00', 2, false), (30, '2023-01-31T10:20:00', 2, false), (30, '2023-01-31T10:40:00', 2, false),
         (30, '2023-01-31T11:00:00', 2, false), (30, '2023-01-31T11:20:00', 2, false), (30, '2023-01-31T11:40:00', 2, false),
         (30, '2023-01-31T12:00:00', 2, false), (30, '2023-01-31T12:20:00', 2, false), (30, '2023-01-31T12:40:00', 2, false),
         (30, '2023-01-31T13:00:00', 2, false), (30, '2023-01-31T13:20:00', 2, false), (30, '2023-01-31T13:40:00', 2, false),
         (30, '2023-01-31T14:00:00', 2, false), (30, '2023-01-31T14:20:00', 2, false), (30, '2023-01-31T14:40:00', 2, false),
         (30, '2023-01-31T15:00:00', 2, false), (30, '2023-01-31T15:20:00', 2, false), (30, '2023-01-31T15:40:00', 2, false),
         (30, '2023-01-31T16:00:00', 2, false), (30, '2023-01-31T16:20:00', 2, false), (30, '2023-01-31T16:40:00', 2, false),
         -- Center 03 -
         (30, '2023-01-31T08:00:00', 3, false), (30, '2023-01-31T08:20:00', 3, false), (30, '2023-01-31T08:40:00', 3, false),
         (30, '2023-01-31T09:00:00', 3, false), (30, '2023-01-31T09:20:00', 3, false), (30, '2023-01-31T09:40:00', 3, false),
         (30, '2023-01-31T10:00:00', 3, false), (30, '2023-01-31T10:20:00', 3, false), (30, '2023-01-31T10:40:00', 3, false),
         (30, '2023-01-31T11:00:00', 3, false), (30, '2023-01-31T11:20:00', 3, false), (30, '2023-01-31T11:40:00', 3, false),
         (30, '2023-01-31T12:00:00', 3, false), (30, '2023-01-31T12:20:00', 3, false), (30, '2023-01-31T12:40:00', 3, false),
         (30, '2023-01-31T13:00:00', 3, false), (30, '2023-01-31T13:20:00', 3, false), (30, '2023-01-31T13:40:00', 3, false),
         (30, '2023-01-31T14:00:00', 3, false), (30, '2023-01-31T14:20:00', 3, false), (30, '2023-01-31T14:40:00', 3, false),
         (30, '2023-01-31T15:00:00', 3, false), (30, '2023-01-31T15:20:00', 3, false), (30, '2023-01-31T15:40:00', 3, false),
         (30, '2023-01-31T16:00:00', 3, false), (30, '2023-01-31T16:20:00', 3, false), (30, '2023-01-31T16:40:00', 3, false);

--	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
--		values(0.5, 'A', 30, '2023-01-31T07:00:00', 1, 6, false);
--	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
--		values(0.5, 'A', 45, '2023-02-05T09:00:00', 1, 8, true);
--	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
--		values(0.5, 'A', 30, '2023-01-31T07:00:00', 2, 8, false);
--    insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
--        values(0.5, 'AB', 30, '2020-12-31T07:00:00', 3, 6, false);

-- ======================================================================
-- BLOOD STORE
    insert into blood_storages(storage_id, storage_ap, storage_an, storage_abp, storage_abn,
     storage_bp, storage_bn, storage_op, storage_on, medical_center_center_id)
        values(1, 10000,10000,8000,8000,11000,11000,9000,9000, 1);
    insert into blood_storages(storage_id, storage_ap, storage_an, storage_abp, storage_abn,
         storage_bp, storage_bn, storage_op, storage_on, medical_center_center_id)
            values(2, 8000,8000,7000,7000,6000,6000,5000,5000, 2);
--    insert into blood_storages(storage_id, storage_a, storage_ab, storage_b, storage_o, medical_center_center_id)
--        values(2, 9.4 ,5, 15 ,8, 2);
