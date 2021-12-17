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

public class Employee_Adapter extends RecyclerView.Adapter<Employee_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<Employee> mUploads;

    public Employee_Adapter(Context context, List<Employee> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.d_d_customer, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {


        holder.fname.setText(mUploads.get(position).getFirstName());
        holder.lname.setText(mUploads.get(position).getLastName());
        holder.email.setText(mUploads.get(position).getEmail());
        holder.phone_no.setText("PHONE: " + mUploads.get(position).getPhoneNo());
        holder.id.setText("ID: " + mUploads.get(position).getId());
        holder.type.setText((mUploads.get(position).getType()));
        holder.sales.setText(("SALES: " + mUploads.get(position).getSales()));
        holder.salary.setText(("SALARY: " +mUploads.get(position).getSalary()));
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView fname, lname, email, phone_no,type,salary,sales,id;
        public ImageViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            phone_no = itemView.findViewById(R.id.phone_no);
            email = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.dealer_id);
            type = itemView.findViewById((R.id.type));
            salary = itemView.findViewById(R.id.salary);
            sales = itemView.findViewById(R.id.n_sales);
        }
    }
}