package com.hackathon.smarthealth.Activity;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathon.smarthealth.Data.User;
import com.hackathon.smarthealth.MainActivity;
import com.hackathon.smarthealth.R;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends AppCompatActivity {
    String personName,personEmail,personPhoto,Address,Blood_group,Guardian_name,Guardian_phone,Guardian_address,Work_location;
    TextView AddressTextView,BloodGroupTextView,GuardianNameTextView,GuardianPhoneTextView,GuardianAddressTextView,WorkLocationTextView;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String userName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseApp.initializeApp(this);

        Intent intent = getIntent();

        personName = intent.getStringExtra("personName");
        personEmail = intent.getStringExtra("personEmail");
        personPhoto = intent.getStringExtra("personPhoto");

        userName = personEmail.split("@")[0];

        TextView user_full_name = (TextView) findViewById(R.id.user_full_name);
        user_full_name.setText(personName);

        TextView user_email = (TextView) findViewById(R.id.user_email);
        user_email.setText(personEmail);

        AddressTextView = (TextView) findViewById(R.id.Address);
        BloodGroupTextView = (TextView) findViewById(R.id.Blood_group);
        GuardianNameTextView = (TextView) findViewById(R.id.Guardian_name);
        GuardianPhoneTextView = (TextView) findViewById(R.id.Guardian_phone);
        GuardianAddressTextView = (TextView) findViewById(R.id.Guardian_address);
        WorkLocationTextView = (TextView) findViewById(R.id.Work_location);



        ImageView userIcon = (ImageView) findViewById(R.id.image_user_profile);

        Picasso.get()
                .load(personPhoto)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(userIcon);

        LinearLayout profile = (LinearLayout)findViewById(R.id.back);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });

        LinearLayout save = (LinearLayout)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeNewUser(personName,personEmail,personPhoto,AddressTextView.getText().toString(),BloodGroupTextView.getText().toString(),GuardianNameTextView.getText().toString(),GuardianPhoneTextView.getText().toString(),GuardianAddressTextView.getText().toString(),WorkLocationTextView.getText().toString());

            }
        });

        try{
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child ( "User_data" ).child ( userName );

        }catch (Exception e){
            Toast.makeText(ProfileActivity.this, "Please update your sensitive information", Toast.LENGTH_SHORT).show();
        }



        // Write a message to the database


        //myRef.setValue("Hello, World!");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                try{
                    Address = dataSnapshot.child ( "Address" ).getValue ().toString ();
                    Blood_group = dataSnapshot.child ( "Blood_group" ).getValue ().toString ();
                    Guardian_name = dataSnapshot.child ( "Guardian_name" ).getValue ().toString ();
                    Guardian_phone = dataSnapshot.child ( "Guardian_phone" ).getValue ().toString ();
                    Guardian_address= dataSnapshot.child ( "Guardian_address" ).getValue ().toString ();
                    Work_location = dataSnapshot.child( "Work_location" ).getValue().toString();

                    AddressTextView.setText(Address);
                    BloodGroupTextView.setText(Blood_group);
                    GuardianNameTextView.setText(Guardian_name);
                    GuardianPhoneTextView.setText(Guardian_phone);
                    GuardianAddressTextView.setText(Guardian_address);
                    WorkLocationTextView.setText(Work_location);
                }catch (Exception e){
                    Toast.makeText(ProfileActivity.this, "Please update your sensitive information", Toast.LENGTH_SHORT).show();
                }



                //Log.e("ds", "Value is: " + Address);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Ds", "Failed to read value.", error.toException());
            }
        });
    }

    private void writeNewUser(String personName,String personEmail,String personPhoto,String Address,String Blood_group,String Guardian_name,String Guardian_phone,String Guardian_address,String Work_location) {
        try{
            User user = new User(personName,personEmail,personPhoto,Address,Blood_group,Guardian_name,Guardian_phone,Guardian_address,Work_location);
            database.getReference().child("User_data").child(userName).setValue(user);
            supportFinishAfterTransition();

        }catch (Exception e){
            Toast.makeText(this, e+"Try After Some Time!!!", Toast.LENGTH_SHORT).show();
            Log.e("dsk",e.toString());
        }
    }
}
