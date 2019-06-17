package com.ahtazaz.client_application_tech_exercise.landingview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ahtazaz.client_application_tech_exercise.R;

public class PriceViewHolder extends RecyclerView.ViewHolder {

    public TextView time;
    public TextView buy;
    public TextView sell;

    public PriceViewHolder(@NonNull View itemView) {
        super(itemView);

        time = itemView.findViewById(R.id.time);
        buy = itemView.findViewById(R.id.priceBuy);
        sell = itemView.findViewById(R.id.priceSell);
    }
}
