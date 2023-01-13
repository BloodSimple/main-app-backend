package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.DonationForm;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
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

    public static DonationForm MapToDonationForm(Map<String,String> map){
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
