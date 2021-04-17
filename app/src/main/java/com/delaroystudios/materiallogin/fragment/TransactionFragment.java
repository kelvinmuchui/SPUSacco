package com.delaroystudios.materiallogin.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.materiallogin.Adpater.TransactionAdapter;
import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.model.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class TransactionFragment extends Fragment {

    RecyclerView recyclerView;
    TransactionAdapter transactionAdapter;
    List<Transaction> transactionList;
    FirebaseFirestore db;
    public TransactionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        recyclerView = view.findViewById(R.id.trans_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = FirebaseFirestore.getInstance();
        transactionList = new ArrayList<>();
        getMyTransactions();


        return view;
    }


    private void  getMyTransactions(){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String transId = currentFirebaseUser.getUid();
        CollectionReference transRef = db.collection("Transaction");


        Query transQuery =transRef
                .whereEqualTo("transaction_account_id", transId);

        transQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {

                        Transaction transactionModel = documentSnapshot.toObject(Transaction.class);
                        transactionList.add(transactionModel);

                        transactionAdapter = new TransactionAdapter(getContext(),transactionList);
                        recyclerView.setAdapter(transactionAdapter);
                    }
                    }
            }
        });
    }


}
