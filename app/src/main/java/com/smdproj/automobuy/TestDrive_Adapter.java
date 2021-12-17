package com.smdproj.automobuy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestDrive_Adapter extends RecyclerView.Adapter<TestDrive_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<TestDriveForm> mUploads;

    public TestDrive_Adapter(Context context, List<TestDriveForm> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.d_d_reg_form, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.fname.setText(mUploads.get(position).getFirstName());
        holder.lname.setText(mUploads.get(position).getLastName());
        holder.email.setText(mUploads.get(position).getEmail());
        holder.phone_no.setText("PHONE: " + mUploads.get(position).getPhoneNo());
        holder.cnic.setText("CNIC: " + mUploads.get(position).getCnic());
        holder.date.setText(mUploads.get(position).getDate());
        holder.id.setText("ID: " +mUploads.get(position).getId());
        holder.vehicle.setText("VEHICLE: " + mUploads.get(position).getVehicle());
        holder.outlet.setText(mUploads.get(position).getOutlet());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView fname, lname, email, cnic, phone_no, vehicle, outlet, date, id;
        public ImageViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            cnic = itemView.findViewById(R.id.cnic);
            phone_no = itemView.findViewById(R.id.phone_no);
            email = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.testdrive_id);
            vehicle = itemView.findViewById(R.id.car_name);
            outlet = itemView.findViewById(R.id.car_outlet);
            date = itemView.findViewById(R.id.car_date);

        }
    }
}


