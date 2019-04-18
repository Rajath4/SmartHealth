package com.hackathon.smarthealth.Data;

public class HospitalDetailsData {
    public String hospital_id;
    public String hospital_name;
    public String hospital_address;
    public String hospital_pincode;
    public String hospital_phone;

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_pincode() {
        return hospital_pincode;
    }

    public void setHospital_pincode(String hospital_pincode) {
        this.hospital_pincode = hospital_pincode;
    }

    public String getHospital_phone() {
        return hospital_phone;
    }

    public void setHospital_phone(String hospital_phone) {
        this.hospital_phone = hospital_phone;
    }

    public String getHospital_rating() {
        return hospital_rating;
    }

    public void setHospital_rating(String hospital_rating) {
        this.hospital_rating = hospital_rating;
    }

    public HospitalDetailsData(String hospital_id, String hospital_name, String hospital_address, String hospital_pincode, String hospital_phone, String hospital_rating) {
        this.hospital_id = hospital_id;
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
        this.hospital_pincode = hospital_pincode;
        this.hospital_phone = hospital_phone;
        this.hospital_rating = hospital_rating;
    }

    String hospital_rating;
}
