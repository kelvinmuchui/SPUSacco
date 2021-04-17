package com.delaroystudios.materiallogin.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.updates;

import java.util.List;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyHolder> {

    Context context;
    List<updates> usersList;


    public UpdateAdapter(Context context, List<updates> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_update,parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {




        String userName = usersList.get(i).getUpdate_account_id();
        String userEmail = usersList.get(i).getUpdate_message();

        //set data
        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                Intent intent = new Intent(context, ChatActivity.class);
//                intent.putExtra("hisUid", hisUID);
//                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    //view holder class
    class MyHolder extends RecyclerView.ViewHolder{



        TextView mNameTv,mEmailTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);


            mNameTv = itemView.findViewById(R.id.updateAccount);
            mEmailTv = itemView.findViewById(R.id.updateMessage);
        }
    }
}

