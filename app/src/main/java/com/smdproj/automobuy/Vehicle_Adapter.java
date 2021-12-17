package com.smdproj.automobuy;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Vehicle_Adapter extends RecyclerView.Adapter<Vehicle_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<Vehicles> mUploads;

    public Vehicle_Adapter(Context context, List<Vehicles> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.vehicle_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        final int pos = position;
        Glide.with(mContext)
                .load(mUploads.get(position).getImg())
                .into(holder.photo);

        holder.carName.setText(mUploads.get(position).getName());
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PurchaseCar.class);
                intent.putExtra("vehicle", mUploads.get(pos));
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView carName;
        public ImageViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.carImage);
            carName = itemView.findViewById(R.id.carName);

        }
    }
}


