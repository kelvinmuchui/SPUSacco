package com.delaroystudios.materiallogin.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.Investment;

import java.util.List;

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.MyHolder>  {

    List<Investment> investmentList;

    public InvestmentAdapter( List<Investment> investmentList) {

        this.investmentList = investmentList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_investment,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentAdapter.MyHolder holder, int i) {

        String name = investmentList.get(i).getInvestment_name();
        String perion = investmentList.get(i).getInvestment_period();


        holder.mNameTv.setText(name);
        holder.mPeriodTv.setText(perion + "Months");
    }

    @Override
    public int getItemCount() {
        return investmentList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mPeriodTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mNameTv = itemView.findViewById(R.id.typetv2);
            mPeriodTv= itemView.findViewById(R.id.period);
        }
    }
}
