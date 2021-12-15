package com.smdproj.automobuy;

import android.content.Context;
import android.content.Intent;
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
        View v = LayoutInflater.from(mContext).inflate(R.layout.temp_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mUploads.get(position).getImg())
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        public ImageViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}


