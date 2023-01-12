package com.ftn.e2.isa.blood_simple.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "DonationForm")
public class DonationForm {

    // generate unique id for DonationForm
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_form_id")
    private Long id;

    @Column(name = "donation_form_date", nullable = false)
    private LocalDateTime date;

    // Questions:
    // 01->19
    @Column(name = "q01")
    private boolean question1; //Have you ever voluntarily donated blood or blood components?
    @Column(name = "q02")
    private boolean question2; //Have you ever been rejected as a blood or blood component donor?
    @Column(name = "q03")
    private boolean question3; //Do you currently feel healthy, fit and rested to donate blood or blood components?
    @Column(name = "q04")
    private boolean question4; //Did you eat anything before coming to donate blood or blood components?
    @Column(name = "q05")
    private boolean question5; //Do you have a dangerous occupation or hobby?
    @Column(name = "q06")
    private boolean question6; //Do you take any medications regularly (daily)?
    @Column(name = "q7")
    private boolean question7; //Have you taken any medicines (eg Brufen, Cafetin, Analgin...) in the last 2-3 days?
    @Column(name = "q08")
    private boolean question8; // Do you regularly take Aspirin (Cardiopirin)? Have you taken it in the last 5 days?
    @Column(name = "q09")
    private boolean question9; //Have you ever been examined or treated in a hospital or are you currently undergoing examination or sick leave?
    @Column(name = "q10")
    private boolean question10; //Have you had a tooth extracted in the past 7 days?
    @Column(name = "q11")
    private boolean question11; //In the last 7 to 10 days, have you had a temperature over 38C, a sneeze, a cold, or taken antibiotics?
    @Column(name = "q12")
    private boolean question12; //Have you received any vaccine or serum in the past 12 months?
    @Column(name = "q13")
    private boolean question13; //Have you suddenly lost weight in the last 6 months?
    @Column(name = "q14")
    private boolean question14; //Have you had tick bites in the past 12 months and have you seen a doctor?
    @Column(name = "q15")
    private boolean question15; //Have you ever been treated for epilepsy, diabetes, asthma, tuberculosis, heart attack, stroke, malignant diseases, mental diseases or malaria?
    @Column(name = "q16")
    private boolean question16; //Do you suffer from any other chronic disease: heart, lungs, kidneys, liver, stomach and intestines, bones and joints,nervous system, blood and blood vessels?
    @Column(name = "q17")
    private boolean question17; //Have you ever had thyroid, pituitary, and/or hormone problems?
    @Column(name = "q18")
    private boolean question18; //Do you have any changes in your skin or suffer from allergies?
    @Column(name = "q19")
    private boolean question19; //Do you bleed for a long time after an injury or do you bruise spontaneously?
    // 20 - a,b,c
    @Column(name = "q20a")
    private boolean question20a; //In the past 6 months, have you: a) had an operation or received blood?
    @Column(name = "q20b")
    private boolean question20b; //In the past 6 months, have you: b) traveled or lived abroad?
    @Column(name = "q20c")
    private boolean question20c; //In the past 6 months, have you: c) had acupuncture, piercing or tattoo?
    // 21
    @Column(name = "q21")
    private boolean question21; //Have you ever voluntarily donated blood or blood components?
    // 22 - a,b,c,d,e,f,g
    @Column(name = "q22a")
    private boolean question22a; // a) Have you suffered or are you suffering from hepatitis (jaundice) A, B or C?
    @Column(name = "q22b")
    private boolean question22b; // b) Have you been in contact with or live with a person suffering from hepatitis (jaundice)?
    @Column(name = "q22c")
    private boolean question22c; // c) Do you think there was a possibility of contracting HIV?
    @Column(name = "q22d")
    private boolean question22d; // d) Have you ever used any type of drug?
    @Column(name = "q22e")
    private boolean question22e; // e) Have you ever used over-the-counter preparations and/or bodybuilding preparations (steroids)?
    @Column(name = "q22f")
    private boolean question22f; // f) Have you ever taken money or drugs for providing sexual services?
    @Column(name = "q22g")
    private boolean question22g; // g) Do you know all the ways you could have exposed yourself to the risk of infectious, blood-borne diseases?
    // 23 - a,b,c,d,e,f
    @Column(name = "q23a")
    private boolean question23a; //  Have you had unprotected sex during the past 6 months: a) with a person who is HIV positive?
    @Column(name = "q23b")
    private boolean question23b; // Have you had unprotected sex during the past 6 months: b) with a person who has or had hepatitis B or C?
    @Column(name = "q23c")
    private boolean question23c; // Have you had unprotected sex during the past 6 months: c) with a person who has ever taken money or drugs for providing sexual services?
    @Column(name = "q23d")
    private boolean question23d; // Have you had unprotected sex during the past 6 months: d) with a person who has ever used any type of drug in any way?
    @Column(name = "q23e")
    private boolean question23e; // Have you had unprotected sex during the past 6 months: e) with a person whose previous sexual behavior could put you at risk of contracting a sexually transmitted disease?
    @Column(name = "q23f")
    private boolean question23f; // Have you had unprotected sex during the past 6 months: f) have you had anal sex during the past 6 months?
    // 24 -26
    @Column(name = "q24")
    private boolean question24; // Are you in another state?
    @Column(name = "q25")
    private boolean question25; // Are you currently on your period?
    @Column(name = "q26")
    private boolean question26; // In the last 6 months, have you given birth or had a termination of pregnancy?
}
