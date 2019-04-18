package com.hackathon.smarthealth.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.smarthealth.Activity.LoginActivity;
import com.hackathon.smarthealth.Activity.SendInfomationToHospitalActivity;
import com.hackathon.smarthealth.Data.HospitalDetailsData;
import com.hackathon.smarthealth.R;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class HospitalDetailsAdapter extends RecyclerView.Adapter<HospitalDetailsAdapter.ViewHolder> {
    private static final String LOG_TAG = HospitalDetailsAdapter.class.getSimpleName();
    public static int position;
    ItemListener mListener;
    Context context;
    private ArrayList<HospitalDetailsData> mItems;

    public HospitalDetailsAdapter(Activity context, ArrayList<HospitalDetailsData> program, ItemListener listener) {

        this.context = context;
        mItems = program;
        mListener = listener;
    }

    public void setOnItemClickListener(ItemListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.hospital_info_card, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }

    public interface ItemListener {
        void onItemClick(HospitalDetailsData pName, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public HospitalDetailsData pName;
        TextView name, detail;
        View textContainer;
        ImageView eventicon;

        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            cardView = itemView.findViewById(R.id.cvItem);
            name = itemView.findViewById(R.id.eventname);
            detail = itemView.findViewById(R.id.eventshortdetail);
            eventicon = itemView.findViewById(R.id.eventicon);
            textContainer = itemView.findViewById(R.id.text_container);
        }

        public void setData(HospitalDetailsData pName) {
            this.pName = pName;
            name.setText(pName.getHospital_name());
            detail.setText(pName.getHospital_address());
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(pName, getAdapterPosition());
            }
            Intent gotoNext = new Intent(context, SendInfomationToHospitalActivity.class);
            gotoNext.putExtra("hospital_id", pName.getHospital_id());
            gotoNext.putExtra("hospital_name", pName.getHospital_name());
            gotoNext.putExtra("hospital_address", pName.getHospital_address());
            gotoNext.putExtra("hospital_pincode", pName.getHospital_pincode());
            gotoNext.putExtra("hospital_phone", pName.getHospital_phone());
            context.startActivity(gotoNext);
        }

    }
}