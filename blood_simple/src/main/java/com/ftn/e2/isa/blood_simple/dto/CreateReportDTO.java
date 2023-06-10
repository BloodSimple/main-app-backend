package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.Equipment;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.Report;

import javax.persistence.Column;

public class CreateReportDTO {

    public Long appointmentId;
    public Long medicalCenterId;

    public double bloodAmount;
    public String doctorNote;

    public String bloodType;

    public String puncture_site;

    public String coppsulf;

    public String coppsulfNormal;

    public String coppsulfLow;

    public String hemoometer;

    public String readValue;

    public String lungs;

    public String heart;

    public String ta;

    public String tt;

    public String tv;

    public String refusalReason;



    //Equipment


    public int sbag150;

    public int sbag400;

    public int sbag600;

    public int dbag150;

    public int dbag400;

    public int dbag600;

    public int tbag150;

    public int tbag400;

    public int tbag600;

    public int bloodLancet;



    public Report dto2Model(CreateReportDTO dto, Appointment app) {
        Report model = new Report(dto, app);
        return model;
    }

    public Equipment getSpentEquipment()
    {
        Equipment e = new Equipment();
        e.setBloodLancet(this.bloodLancet);
        e.setSbag150(this.sbag150);
        e.setSbag400(this.sbag400);
        e.setSbag600(this.sbag600);

        e.setDbag150(this.dbag150);
        e.setDbag400(this.dbag400);
        e.setDbag600(this.dbag600);

        e.setTbag150(this.tbag150);
        e.setTbag400(this.tbag400);
        e.setTbag600(this.tbag600);
        return e;
    }

}
