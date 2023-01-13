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
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('2310999760011', 'dule@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN');
            -- user's authority [SYSTEM_ADMIN=1, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (1, 1);
            insert into USER_ROLE(user_id, authority_id) values (1, 4);
    -- CenterAdmins and Medical Staff
        -- id=2 -- in the database -- [CenterAdmin to MedicalCenter1] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('1212199760011', 'niki@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'MEDICAL_ADMIN');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (2, 2);
            insert into USER_ROLE(user_id, authority_id) values (2, 4);
        -- id=3 -- in the database -- [CenterAdmin to MedicalCenter2] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('743199760011', 'lale@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (3, 2);
            insert into USER_ROLE(user_id, authority_id) values (3, 4);
        -- id=4 -- in the database -- [MedicalStaff to MedicalCenter2]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('145299763041', 'ble@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Boris', 'Tadic','MALE', 4, '063132156', 'menadzer', 'Unemployed manager ', 'MEDICAL_ADMIN');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (4, 2);
            insert into USER_ROLE(user_id, authority_id) values (4, 4);
        -- id=5 -- in the database -- [MedicalStaff to MedicalCenter3]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('145299763044', 'tijana@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Tijana', 'Tijanic','FEMALE', 4, '06313215654', 'med.sestra', 'Med.sestra', 'MEDICAL_ADMIN');
        -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
        insert into USER_ROLE(user_id, authority_id) values (5, 2);
        insert into USER_ROLE(user_id, authority_id) values (5, 4);
    -- RegisteredUsers
        -- id=6 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('2310987760011', 'miki@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Miki', 'Mikic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (6, 3);
            insert into USER_ROLE(user_id, authority_id) values (6, 4);
        -- id=7 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('35445421760011', 'jankovicmaraja99@gmail.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Marija', 'Jankovic','FEMALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER');
            -- user's authority [MEDICAL_ADMIN=2, COMMON=4]
            insert into USER_ROLE(user_id, authority_id) values (7, 3);
            insert into USER_ROLE(user_id, authority_id) values (7, 4);
        -- id=8 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('35445421760022', 'sima@email.com', '$2a$10$Ps50dvJRaYXRKturARqvme4lp6H7fTfFpritYZCAM2vZhZFASeq56', 'Sima', 'Simic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER');
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
	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
		values(0.5, 'A', 30, '2023-01-31T07:00:00', 1, 6, false);
	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
		values(0.5, 'A', 45, '2023-02-05T09:00:00', 1, 8, true);
	insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
		values(0.5, 'A', 30, '2023-01-31T07:00:00', 2, 8, false);
    insert into appointments(amount_of_blood, blood_type, appointment_duration, appointment_start, medical_center_center_id, user_user_id, reserved)
        values(0.5, 'AB', 30, '2020-12-31T07:00:00', 3, 6, false);
