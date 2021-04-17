package com.delaroystudios.materiallogin.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delaroystudios.materiallogin.R;

import com.delaroystudios.materiallogin.model.Loan;
import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.MyHolder> {
    Context context;
    List<Loan> transactionList;

    public LoanAdapter(List<Loan> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_loan,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanAdapter.MyHolder holder, int i) {


        double amount = transactionList.get(i).getLoan_amount();
        String mode = String.valueOf(transactionList.get(i).getLoan_period());


        holder.mAmountTv.setText(String.valueOf(amount));
        holder.mModeTv.setText(mode + " Months");



    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{

        TextView mDateTv, mAmountTv, mModeTv;



        public MyHolder(@NonNull View itemView) {
            super(itemView);


            //init views

            mModeTv = itemView.findViewById(R.id.loantv);
            mAmountTv = itemView.findViewById(R.id.loanAmount);



        }
    }
}
