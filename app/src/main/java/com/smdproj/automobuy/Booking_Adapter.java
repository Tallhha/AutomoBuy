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

public class Booking_Adapter extends RecyclerView.Adapter<Booking_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<BookingForm> mUploads;

    public Booking_Adapter(Context context, List<BookingForm> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.d_d_manage, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.fname.setText(mUploads.get(position).getFirstName());
        holder.lname.setText(mUploads.get(position).getLastName());
        holder.email.setText(mUploads.get(position).getEmail());
        holder.phone_no.setText("PHONE: " + mUploads.get(position).getPhoneNo());
        holder.id.setText("ID: " + mUploads.get(position).getId());

        holder.car_name.setText(mUploads.get(position).getVehicle());
        holder.car_cost.setText(mUploads.get(position).getCost());
        holder.car_color.setText(mUploads.get(position).getColor());
        holder.car_type.setText(mUploads.get(position).getType());
        holder.car_model.setText("MODEL" + mUploads.get(position).getModel());
        holder.buying_option.setText(mUploads.get(position).getBuyingOption());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView fname, lname, email, phone_no, id;
        TextView buying_option, car_color, car_type, car_cost, car_model, car_name;

        public ImageViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            phone_no = itemView.findViewById(R.id.phone_no);
            email = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.booking_id);

            buying_option = itemView.findViewById(R.id.buying_option);
            car_color = itemView.findViewById(R.id.car_color);
            car_type = itemView.findViewById(R.id.car_type);
            car_cost = itemView.findViewById(R.id.car_cost);
            car_model = itemView.findViewById(R.id.car_model);
            car_name = itemView.findViewById(R.id.car_name);

        }
    }
}

