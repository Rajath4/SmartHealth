package com.hackathon.smarthealth.Data;

public class ComplaintRequestData {


    public ComplaintRequestData(String hospital_id, String hospital_name, String hospital_address, String hospital_pincode, String hospital_phone, String latitude, String longitude, String complaintStatus, String complaintTime, String personName, String personEmail, String personPhoto, String address, String blood_group, String guardian_name, String guardian_phone, String guardian_address, String work_location, String note, Boolean reportedByOther) {
        this.hospital_id = hospital_id;
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
        this.hospital_pincode = hospital_pincode;
        this.hospital_phone = hospital_phone;
        this.latitude = latitude;
        this.longitude = longitude;
        ComplaintStatus = complaintStatus;
        ComplaintTime = complaintTime;
        this.personName = personName;
        this.personEmail = personEmail;
        this.personPhoto = personPhoto;
        Address = address;
        Blood_group = blood_group;
        Guardian_name = guardian_name;
        Guardian_phone = guardian_phone;
        Guardian_address = guardian_address;
        Work_location = work_location;
        this.note = note;
        this.reportedByOther = reportedByOther;
    }

    public String hospital_id;
    public String hospital_name;
    public String hospital_address;
    public String hospital_pincode;
    public String hospital_phone;
    public String latitude,longitude;
    public String ComplaintStatus;
    public String ComplaintTime;
    public String personName;
    public String personEmail;
    public String personPhoto;
    public String Address;
    public String Blood_group;
    public String Guardian_name;
    public String Guardian_phone;
    public String Guardian_address;
    public String Work_location;
    public String note;
    public Boolean reportedByOther;


}
