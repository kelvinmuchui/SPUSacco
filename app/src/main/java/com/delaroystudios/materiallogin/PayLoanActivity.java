package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.delaroystudios.materiallogin.Adpater.InvestmentAdapter;
import com.delaroystudios.materiallogin.Adpater.LoanAdapter;
import com.delaroystudios.materiallogin.model.Investment;
import com.delaroystudios.materiallogin.model.Loan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PayLoanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LoanAdapter investmentAdapter;
    List<Loan> investmentList;
    FloatingActionButton fab;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_loan);

        recyclerView = findViewById(R.id.invest_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        investmentList = new ArrayList<>();
        getMyLoan();
    }

    private void getMyLoan() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String transId = currentFirebaseUser.getUid();
        CollectionReference investRef = db.collection("Loan");

        Query investQuery =investRef
                .whereEqualTo("loan_account_id", transId);


        investQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {

                        Loan investmentModel = documentSnapshot.toObject(Loan.class);
                        investmentList.add(investmentModel);

                        investmentAdapter = new LoanAdapter(investmentList);
                        recyclerView.setAdapter(investmentAdapter);
                    }
                }
            }
        });
    }
}
