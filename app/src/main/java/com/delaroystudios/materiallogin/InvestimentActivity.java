package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.delaroystudios.materiallogin.Adpater.InvestmentAdapter;
import com.delaroystudios.materiallogin.Adpater.TransactionAdapter;
import com.delaroystudios.materiallogin.model.Account;
import com.delaroystudios.materiallogin.model.Investment;
import com.delaroystudios.materiallogin.model.Member;
import com.delaroystudios.materiallogin.model.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class InvestimentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InvestmentAdapter investmentAdapter;
    List<Investment> investmentList;
    FloatingActionButton fab;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investiment);

        fab = findViewById(R.id.floatingActionButton);

        recyclerView = findViewById(R.id.invest_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        investmentList = new ArrayList<>();

        getMyInvestment();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InvestimentActivity.this);
                builder.setTitle("Enter Investment details");

                LinearLayout linearLayout = new LinearLayout(InvestimentActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10,10,10,10);

                final EditText editText = new EditText(InvestimentActivity.this);
                final EditText editText2 = new EditText(InvestimentActivity.this);
                final EditText editText3 = new EditText(InvestimentActivity.this);
                editText.setHint("Enter Investment Name");
                editText2.setHint("Enter Period in Months");
                editText3.setHint("Enter Amount");
                linearLayout.addView(editText);
                linearLayout.addView(editText2);
                linearLayout.addView(editText3);


                builder.setView(linearLayout);
                builder.setPositiveButton("Deposit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String amount = editText.getText().toString().trim();
                        final  String period = editText2.getText().toString().trim();
                        final String myam = editText3.getText().toString().trim();
                        if (TextUtils.isEmpty(amount)){
                            Toast.makeText(InvestimentActivity.this, "Enter Investment type", Toast.LENGTH_SHORT).show();
                        }
                        if (TextUtils.isEmpty(period)){
                            Toast.makeText(InvestimentActivity.this, "Enter Investment period", Toast.LENGTH_SHORT).show();
                        }
                        double inAmount = Double.parseDouble(myam);
                        addInvestment(amount,period);
                        addTrans(inAmount);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }


                });
                builder.create().show();


            }
        });

    }

    private void getMyInvestment() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String transId = currentFirebaseUser.getUid();
        CollectionReference investRef = db.collection("Investment");


        Query investQuery =investRef
                .whereEqualTo("investment_transaction_id", transId);


        investQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {

                        Investment investmentModel = documentSnapshot.toObject(Investment.class);
                        investmentList.add(investmentModel);

                        investmentAdapter = new InvestmentAdapter(investmentList);
                        recyclerView.setAdapter(investmentAdapter);
                    }
                }
            }
        });
    }

    private void addInvestment( String invest_name,String invest_period){
        DocumentReference dbMember = db.collection("Investment").document();



        FirebaseUser user = mAuth.getCurrentUser();

        String uid = user.getUid();
        Investment member = new Investment();
        member.setInvestment_id(dbMember.getId());
        member.setInvestment_name(invest_name);
        member.setInvestment_transaction_id(uid);
        member.setInvestment_period(invest_period);

        dbMember.set(member).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void avoid) {
                Toast.makeText(InvestimentActivity.this, "Investment Add", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InvestimentActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private  void  addTrans(double amount){
        FirebaseUser user = mAuth.getCurrentUser();

        String uid = user.getUid();
        DocumentReference dbTransation = db.collection("Transaction").document();
        Transaction transaction = new Transaction();
        transaction.setTransaction_account_id(uid);
        transaction.setTransaction_amount(amount);
        transaction.setTransaction_id(dbTransation.getId());
        transaction.setTransaction_mode("Investment");
        transaction.setTransaction_time(Timestamp.now());

        dbTransation.set(transaction).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
