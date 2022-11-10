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
-- ======================================================================
-- USERS
    -- SystemAdmin
        -- id=1 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('2310999760011', 'dule@email.com', 'dule23', 'Dusan', 'Markovic','MALE', 6, '0641123456', 'engineer', 'Software Engineer at BloodSimple', 'SYSTEM_ADMIN');
    -- CenterAdmins
        -- id=2 -- in the database -- [CenterAdmin to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('1212199760011', 'niki@email.com', 'dule23', 'Branimir', 'Nestorovic','MALE', 5, '0641123456', 'dr', 'medical doctor at KCL', 'MEDICAL_ADMIN');
        -- id=3 -- in the database -- [CenterAdmin to MedicalCenter2] & [MedicalStaff to MedicalCenter1]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('743199760011', 'lale@email.com', 'dule23', 'Vladimir', 'Lalic','MALE', 4, '0641123456', 'it', 'Network Administrator at KCL ', 'MEDICAL_ADMIN');
    -- MedicalStaff
        -- id=4 -- in the database -- [MedicalStaff]
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('145299763041', 'ble@email.com', 'dule23', 'Boris', 'Tadic','MALE', 4, '063132156', 'menadzer', 'Unemployed manager ', 'MEDICAL_ADMIN');
    -- RegisteredUsers
        -- id=5 -- in the database
        insert into users (user_personal_id, user_email, user_password, user_name, user_surname, user_type, user_address_id, user_phone, user_job, user_bio, user_role)
        values ('2310987760011', 'miki@email.com', '123', 'Miki', 'Mikic','MALE', 3, '0641123456', 'engineer', 'Faculty of Technical Sciences, University of Novi Sad', 'USER');
-- ======================================================================
-- MEDICAL CENTERS -- with CenterAdmins and MedicalStaff
        -- id=1 -- in the database
        insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
        values ('Blood Drop', 'Vadimo krv kap po kap, kisa sprema se... ok prestacu', 2, 2, 3.0);
            -- Medical Staff in the Medical Center
            insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id)
            values ( 1, 3);
            insert into medical_centers_medical_staff (medical_center_center_id, medical_staff_user_id)
            values ( 1, 2);
        -- id=2 -- in the database
        insert into medical_centers (center_name, center_description, center_address_id, center_admin_user_id, grade)
        values ('Klinicki Centar Lab - KCL', 'Dosta smo dobar centar za vadjenje krvi, za to smo top', 1, 3, 5.0);
            -- Medical Staff in the Medical Center
            -- EMPTY ...
-- ======================================================================

