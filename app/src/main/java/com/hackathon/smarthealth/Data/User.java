package com.hackathon.smarthealth.Data;

public class User {
    public String personName;
    public String personEmail;
    public String personPhoto;
    public String Address;
    public String Blood_group;
    public String Guardian_name;
    public String Guardian_phone;
    public String Guardian_address;
    public String Work_location;
    public User(String personName, String personEmail, String personPhoto, String address, String blood_group, String guardian_name, String guardian_phone, String guardian_address, String work_location) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.personPhoto = personPhoto;
        Address = address;
        Blood_group = blood_group;
        Guardian_name = guardian_name;
        Guardian_phone = guardian_phone;
        Guardian_address = guardian_address;
        Work_location = work_location;
    }
}
