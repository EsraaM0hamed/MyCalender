package com.desai.vatsal.mydynamiccalendarexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DateListAdapterE extends RecyclerView.Adapter<DateListAdapterE.ViewHolder> {

    private Context context;
    private ArrayList<DateModelE> dateModelList;


    public DateListAdapterE(Context context, ArrayList<DateModelE> dateModelList) {
        this.context = context;
        this.dateModelList = dateModelList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DateListAdapterE.ViewHolder holder, int position) {
        holder.app_iamge.setImageResource(Integer.parseInt(String.valueOf(dateModelList.get(position).getFlag())));
    }

    @Override
    public int getItemCount() {
        return dateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView app_iamge;
        TextView app_name;


        public ViewHolder(View itemView) {
            super(itemView);
            app_iamge = (ImageView) itemView.findViewById(R.id.symbol);


        }
    }
}
