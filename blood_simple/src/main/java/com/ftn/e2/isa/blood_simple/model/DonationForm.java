package com.ftn.e2.isa.blood_simple.model;

import com.ftn.e2.isa.blood_simple.dto.DonationFormDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data        // @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DonationForm")
public class DonationForm {

    // generate unique id for DonationForm
    @Id
    @GeneratedValue
    @Column(name = "donation_form_id")
    private Long id;


    @Column(name = "user_id")
    private Long userId;


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
    private boolean question21; // Have you drunk alcohol in the last 6 hours?
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

    public DonationForm(DonationFormDTO dto) {
        this.setDonationForm(dto);
    }

    public DonationForm(LocalDateTime date, boolean question1, boolean question2, boolean question3, boolean question4, boolean question5, boolean question6, boolean question7, boolean question8, boolean question9, boolean question10, boolean question11, boolean question12, boolean question13, boolean question14, boolean question15, boolean question16, boolean question17, boolean question18, boolean question19, boolean question20a, boolean question20b, boolean question20c, boolean question21, boolean question22a, boolean question22b, boolean question22c, boolean question22d, boolean question22e, boolean question22f, boolean question22g, boolean question23a, boolean question23b, boolean question23c, boolean question23d, boolean question23e, boolean question23f, boolean question24, boolean question25, boolean question26) {
        this.date = date;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
        this.question8 = question8;
        this.question9 = question9;
        this.question10 = question10;
        this.question11 = question11;
        this.question12 = question12;
        this.question13 = question13;
        this.question14 = question14;
        this.question15 = question15;
        this.question16 = question16;
        this.question17 = question17;
        this.question18 = question18;
        this.question19 = question19;
        this.question20a = question20a;
        this.question20b = question20b;
        this.question20c = question20c;
        this.question21 = question21;
        this.question22a = question22a;
        this.question22b = question22b;
        this.question22c = question22c;
        this.question22d = question22d;
        this.question22e = question22e;
        this.question22f = question22f;
        this.question22g = question22g;
        this.question23a = question23a;
        this.question23b = question23b;
        this.question23c = question23c;
        this.question23d = question23d;
        this.question23e = question23e;
        this.question23f = question23f;
        this.question24 = question24;
        this.question25 = question25;
        this.question26 = question26;
    }

    public void setDonationForm(DonationFormDTO dto) {
        this.date = LocalDateTime.now();
        this.question1 = dto.isQuestion1();
        this.question2 = dto.isQuestion2();
        this.question3 = dto.isQuestion3();
        this.question4 = dto.isQuestion4();
        this.question5 = dto.isQuestion5();
        this.question6 = dto.isQuestion6();
        this.question7 = dto.isQuestion7();
        this.question8 = dto.isQuestion8();
        this.question9 = dto.isQuestion9();
        this.question10 = dto.isQuestion10();
        this.question11 = dto.isQuestion11();
        this.question12 = dto.isQuestion12();
        this.question13 = dto.isQuestion13();
        this.question14 = dto.isQuestion14();
        this.question15 = dto.isQuestion15();
        this.question16 = dto.isQuestion16();
        this.question17 = dto.isQuestion17();
        this.question18 = dto.isQuestion18();
        this.question19 = dto.isQuestion19();
        this.question20a = dto.isQuestion20a();
        this.question20b = dto.isQuestion20b();
        this.question20c = dto.isQuestion20c();
        this.question21 = dto.isQuestion21();
        this.question22a = dto.isQuestion22a();
        this.question22b = dto.isQuestion22b();
        this.question22c = dto.isQuestion22c();
        this.question22d = dto.isQuestion22d();
        this.question22e = dto.isQuestion22e();
        this.question22f = dto.isQuestion22f();
        this.question22g = dto.isQuestion22g();
        this.question23a = dto.isQuestion23a();
        this.question23b = dto.isQuestion23b();
        this.question23c = dto.isQuestion23c();
        this.question23d = dto.isQuestion23d();
        this.question23e = dto.isQuestion23e();
        this.question23f = dto.isQuestion23f();
        this.question24 = dto.isQuestion24();
        this.question25 = dto.isQuestion25();
        this.question26 = dto.isQuestion26();
    }


}
