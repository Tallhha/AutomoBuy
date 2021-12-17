package com.smdproj.automobuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Sales_Adapter extends RecyclerView.Adapter<Sales_Adapter.ImageViewHolder> {

    private Context mContext;
    private List<Sale> mUploads;

    public Sales_Adapter(Context context, List<Sale> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.m_d_sale_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.custname.setText(mUploads.get(position).getCust_name());
        holder.custemail.setText(mUploads.get(position).getCust_email());
        holder.dlrname.setText(mUploads.get(position).getDlr_name());
        holder.dlremail.setText(mUploads.get(position).getDlr_email());
        holder.id.setText(mUploads.get(position).getId());

        holder.car_name.setText(mUploads.get(position).getVehicle());
        holder.car_cost.setText(mUploads.get(position).getCost());
        holder.car_model.setText(mUploads.get(position).getModel());
        holder.buying_option.setText(mUploads.get(position).getBuying_option());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView custname, dlrname, dlremail, custemail, id;
        TextView buying_option, car_type, car_cost, car_model, car_name;

        public ImageViewHolder(View itemView) {
            super(itemView);
            custname = itemView.findViewById(R.id.fname);
            dlremail = itemView.findViewById(R.id.cnic);
            custemail = itemView.findViewById(R.id.phone_no);
            dlrname = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.sale_id);
            buying_option = itemView.findViewById(R.id.buying_option);
            car_type = itemView.findViewById(R.id.car_type);
            car_cost = itemView.findViewById(R.id.car_cost);
            car_model = itemView.findViewById(R.id.car_model);
            car_name = itemView.findViewById(R.id.car_name);

        }
    }
}

