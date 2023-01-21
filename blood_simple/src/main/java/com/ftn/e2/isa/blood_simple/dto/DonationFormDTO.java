package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.DonationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationFormDTO {

    private Long id;
    private LocalDateTime date;
    // Questions:
    // 01->19
    private boolean question1;
    private boolean question2;
    private boolean question3;
    private boolean question4;
    private boolean question5;
    private boolean question6;
    private boolean question7;
    private boolean question8;
    private boolean question9;
    private boolean question10;
    private boolean question11;
    private boolean question12;
    private boolean question13;
    private boolean question14;
    private boolean question15;
    private boolean question16;
    private boolean question17;
    private boolean question18;
    private boolean question19;
    // 20 - a,b,c
    private boolean question20a;
    private boolean question20b;
    private boolean question20c;
    // 21
    private boolean question21;
    // 22 - a,b,c,d,e,f,g
    private boolean question22a;
    private boolean question22b;
    private boolean question22c;
    private boolean question22d;
    private boolean question22e;
    private boolean question22f;
    private boolean question22g;
    // 23 - a,b,c,d,e,f
    private boolean question23a;
    private boolean question23b;
    private boolean question23c;
    private boolean question23d;
    private boolean question23e;
    private boolean question23f;
    // 24->26
    private boolean question24;
    private boolean question25;
    private boolean question26;


    public DonationFormDTO(DonationForm donationForm) {
        this.id = donationForm.getId();
        this.date = donationForm.getDate();
        this.question1 = donationForm.isQuestion1();
        this.question2 = donationForm.isQuestion2();
        this.question3 = donationForm.isQuestion3();
        this.question4 = donationForm.isQuestion4();
        this.question5 = donationForm.isQuestion5();
        this.question6 = donationForm.isQuestion6();
        this.question7 = donationForm.isQuestion7();
        this.question8 = donationForm.isQuestion8();
        this.question9 = donationForm.isQuestion9();
        this.question10 = donationForm.isQuestion10();
        this.question11 = donationForm.isQuestion11();
        this.question12 = donationForm.isQuestion12();
        this.question13 = donationForm.isQuestion13();
        this.question14 = donationForm.isQuestion14();
        this.question15 = donationForm.isQuestion15();
        this.question16 = donationForm.isQuestion16();
        this.question17 = donationForm.isQuestion17();
        this.question18 = donationForm.isQuestion18();
        this.question19 = donationForm.isQuestion19();
        this.question20a = donationForm.isQuestion20a();
        this.question20b = donationForm.isQuestion20b();
        this.question20c = donationForm.isQuestion20c();
        this.question21 = donationForm.isQuestion21();
        this.question22a = donationForm.isQuestion22a();
        this.question22b = donationForm.isQuestion22b();
        this.question22c = donationForm.isQuestion22c();
        this.question22d = donationForm.isQuestion22d();
        this.question22e = donationForm.isQuestion22e();
        this.question22f = donationForm.isQuestion22f();
        this.question22g = donationForm.isQuestion22g();
        this.question23a = donationForm.isQuestion23a();
        this.question23b = donationForm.isQuestion23b();
        this.question23c = donationForm.isQuestion23c();
        this.question23d = donationForm.isQuestion23d();
        this.question23e = donationForm.isQuestion23e();
        this.question23f = donationForm.isQuestion23f();
        this.question24 = donationForm.isQuestion24();
        this.question25 = donationForm.isQuestion25();
        this.question26 = donationForm.isQuestion26();
    }

    public static DonationForm MapToDonationForm(Map<String, String> map) {
        return new DonationForm(
                LocalDateTime.now(),
                Boolean.parseBoolean(map.get("question1")),
                Boolean.parseBoolean(map.get("question2")),
                Boolean.parseBoolean(map.get("question3")),
                Boolean.parseBoolean(map.get("question4")),
                Boolean.parseBoolean(map.get("question5")),
                Boolean.parseBoolean(map.get("question6")),
                Boolean.parseBoolean(map.get("question7")),
                Boolean.parseBoolean(map.get("question8")),
                Boolean.parseBoolean(map.get("question9")),
                Boolean.parseBoolean(map.get("question10")),
                Boolean.parseBoolean(map.get("question11")),
                Boolean.parseBoolean(map.get("question12")),
                Boolean.parseBoolean(map.get("question13")),
                Boolean.parseBoolean(map.get("question14")),
                Boolean.parseBoolean(map.get("question15")),
                Boolean.parseBoolean(map.get("question16")),
                Boolean.parseBoolean(map.get("question17")),
                Boolean.parseBoolean(map.get("question18")),
                Boolean.parseBoolean(map.get("question19")),
                Boolean.parseBoolean(map.get("question20a")),
                Boolean.parseBoolean(map.get("question20b")),
                Boolean.parseBoolean(map.get("question20c")),
                Boolean.parseBoolean(map.get("question21")),
                Boolean.parseBoolean(map.get("question22a")),
                Boolean.parseBoolean(map.get("question22b")),
                Boolean.parseBoolean(map.get("question22c")),
                Boolean.parseBoolean(map.get("question22d")),
                Boolean.parseBoolean(map.get("question22e")),
                Boolean.parseBoolean(map.get("question22f")),
                Boolean.parseBoolean(map.get("question22g")),
                Boolean.parseBoolean(map.get("question23a")),
                Boolean.parseBoolean(map.get("question23b")),
                Boolean.parseBoolean(map.get("question23c")),
                Boolean.parseBoolean(map.get("question23d")),
                Boolean.parseBoolean(map.get("question23e")),
                Boolean.parseBoolean(map.get("question23f")),
                Boolean.parseBoolean(map.get("question24")),
                Boolean.parseBoolean(map.get("question25")),
                Boolean.parseBoolean(map.get("question26"))

        );
    }

}
