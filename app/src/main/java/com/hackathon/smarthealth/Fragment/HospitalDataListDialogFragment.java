package com.hackathon.smarthealth.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hackathon.smarthealth.Activity.ProfileActivity;
import com.hackathon.smarthealth.Adapter.HospitalDetailsAdapter;
import com.hackathon.smarthealth.Data.HospitalDetailsData;
import com.hackathon.smarthealth.R;

import java.util.ArrayList;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     HospitalDataListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class HospitalDataListDialogFragment extends BottomSheetDialogFragment {
    String hospital_id;
    String hospital_name;
    String hospital_address;
    String hospital_pincode;
    String hospital_phone;
    String hospital_rating;

    final ArrayList<HospitalDetailsData> pname = new ArrayList<HospitalDetailsData>();
    View view;
    public static HospitalDataListDialogFragment newInstance() {
        return new HospitalDataListDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_hospitaldata_list_dialog, container,
                false);

        // get the views and attach the listener




        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        new MyAsyncTask().execute();


    }

    //AsyncTask to display collection card
    private class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            //Show animation while loading data
        }

        @Override
        protected String doInBackground(String... params) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference().child ( "Hospital" );

            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    int i=0;
                    for (i=0;i<5;i++){
                        String x = String.valueOf(i);
                        try{
                            hospital_id = dataSnapshot.child(x).child ( "hospital_id" ).getValue ().toString ();
                            hospital_name = dataSnapshot.child(x).child ( "hospital_name" ).getValue ().toString ();
                            hospital_address = dataSnapshot.child(x).child ( "hospital_address" ).getValue ().toString ();
                            hospital_pincode = dataSnapshot.child(x).child ( "hospital_pincode" ).getValue ().toString ();
                            hospital_phone = dataSnapshot.child(x).child ( "hospital_phone" ).getValue ().toString ();
                            hospital_rating = dataSnapshot.child(x).child ( "hospital_rating" ).getValue ().toString ();

                            Log.e("ds", "Value is: " + hospital_name);


                            HospitalDetailsData hospitalDetailsData = new HospitalDetailsData(hospital_id,hospital_name,hospital_address,hospital_pincode,hospital_phone,hospital_rating);
                            pname.add(hospitalDetailsData);
                        }catch (Exception e){
                            Log.e("ds", "Value isxz cxcc: " + e);

                        }


                    }
                    Log.e("Varusssssn",pname.toString());
                    try {
                        final HospitalDetailsAdapter itemsAdapter = new HospitalDetailsAdapter(HospitalDataListDialogFragment.this.getActivity(), pname, null);
                        final RecyclerView clv = view.findViewById(R.id.clist);
                        clv.setLayoutManager(new LinearLayoutManager(HospitalDataListDialogFragment.this.getActivity()));
                        clv.setHasFixedSize(true);
                        clv.setAdapter(itemsAdapter);
                        Log.e("Varun",pname.toString());
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
            return null;
        }

        protected void onPostExecute(String result) {


        }


    }
}