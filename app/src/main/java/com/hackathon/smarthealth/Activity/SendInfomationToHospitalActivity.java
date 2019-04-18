package com.hackathon.smarthealth.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackathon.smarthealth.Data.ComplaintRequestData;
import com.hackathon.smarthealth.Data.User;
import com.hackathon.smarthealth.MainActivity;
import com.hackathon.smarthealth.R;

import java.sql.Timestamp;
import java.util.Date;

public class SendInfomationToHospitalActivity extends AppCompatActivity {
    String hospital_id;
    String hospital_name;
    String hospital_address;
    String hospital_pincode;
    String hospital_phone;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String reportedBy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_infomation_to_hospital);

        Intent intent = getIntent();
        hospital_id = intent.getStringExtra("hospital_id");
        hospital_name = intent.getStringExtra("hospital_name");
        hospital_address = intent.getStringExtra("hospital_address");
        hospital_pincode = intent.getStringExtra("hospital_pincode");
        hospital_phone = intent.getStringExtra("hospital_phone");


        database = FirebaseDatabase.getInstance();

        Date date= new Date();

        long time = date.getTime();

        Timestamp ts = new Timestamp(time);


        if(MainActivity.reportedByOther){
            reportedBy = String.valueOf(time);
        }else {
            reportedBy = MainActivity.userName;
        }

        writeNewComplaint(hospital_id,hospital_name,hospital_address,hospital_pincode,hospital_phone,MainActivity.personName,MainActivity.personEmail,MainActivity.personPhoto,MainActivity.Address,MainActivity.Blood_group,MainActivity.Guardian_name,MainActivity.Guardian_phone,MainActivity.Guardian_address,MainActivity.Work_location, MainActivity.latitude,MainActivity.longitude,"TRUE", ts.toString(),MainActivity.note,MainActivity.reportedByOther);
    }
    private void writeNewComplaint(String hospital_id,String hospital_name,String hospital_address,String hospital_pincode,String hospital_phone,String personName,String personEmail,String personPhoto,String Address,String Blood_group,String Guardian_name,String Guardian_phone,String Guardian_address,String Work_location,String lattitude,String longgitude,String complaintStatus,String complaintTime,String noteAbt,Boolean reportedByOther) {
        try{
            ComplaintRequestData complaintRequestData = new ComplaintRequestData(hospital_id,hospital_name,hospital_address,hospital_pincode,hospital_phone,lattitude,longgitude,complaintStatus,complaintTime,personName,personEmail,personPhoto,Address,Blood_group,Guardian_name,Guardian_phone,Guardian_address,Work_location,noteAbt,reportedByOther);
            database.getReference().child("Complaint_Data").child(reportedBy).setValue(complaintRequestData);
        }catch (Exception e){
            supportFinishAfterTransition();
            Toast.makeText(this, "Try After Some Time!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
