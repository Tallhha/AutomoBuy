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

public class Feedback_Adapter extends RecyclerView.Adapter<Feedback_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<Store_Feedback> mUploads;

    public Feedback_Adapter(Context context, List<Store_Feedback> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.m_d_feedback_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.fname.setText(mUploads.get(position).getFname());
        holder.lname.setText(mUploads.get(position).getLname());
        holder.feedback.setText(mUploads.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView fname, lname, feedback;
        public ImageViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            feedback = itemView.findViewById(R.id.feedback);
        }
    }
}


