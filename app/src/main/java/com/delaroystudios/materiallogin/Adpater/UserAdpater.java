package com.delaroystudios.materiallogin.Adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.Member;

import java.util.List;

public class UserAdpater extends RecyclerView.Adapter<UserAdpater.MyHolder> {

    Context context;
    List<Member> usersList;


    public UserAdpater(Context context, List<Member> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_user,parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {

        final String hisUID = usersList.get(i).getMember_id();


        String userName = usersList.get(i).getMember_name();
        String userEmail = usersList.get(i).getMember_account_id();

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


        ImageView mAvatarIv;
        TextView mNameTv,mEmailTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mAvatarIv = itemView.findViewById(R.id.avatarIv);
            mNameTv = itemView.findViewById(R.id.nameTv);
            mEmailTv = itemView.findViewById(R.id.account_number);
        }
    }
}

