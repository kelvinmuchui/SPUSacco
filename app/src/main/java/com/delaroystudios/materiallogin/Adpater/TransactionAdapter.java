package com.delaroystudios.materiallogin.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.Transaction;
import com.google.firebase.Timestamp;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyHolder> {
  Context context;
  List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_transaction,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyHolder holder, int i) {

        Timestamp timestamp = transactionList.get(i).getTransaction_time();
        Date date = timestamp.toDate();

        String sdate = String.valueOf(date);
        double amount = transactionList.get(i).getTransaction_amount();
        String mode = transactionList.get(i).getTransaction_mode();

        holder.mDateTv.setText(sdate);
        holder.mAmountTv.setText(String.valueOf(amount));
        holder.mModeTv.setText(mode);

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

            mModeTv = itemView.findViewById(R.id.transMode);

        }
    }
}
