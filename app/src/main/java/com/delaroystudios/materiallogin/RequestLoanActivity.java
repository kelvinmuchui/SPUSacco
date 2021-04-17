package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.materiallogin.model.Loan;
import com.delaroystudios.materiallogin.model.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestLoanActivity extends AppCompatActivity {

    EditText amount, period, type;
    TextView amountTv, viewTv;

    Button requests, cancel;

    double mAmount;
    int mPeriod;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_loan);

        amount = findViewById(R.id.input_amount);
        period = findViewById(R.id.input_period);
        type = findViewById(R.id.input_type);

        amountTv = findViewById(R.id.tvamount);
        viewTv = findViewById(R.id.tvView);


        requests = findViewById(R.id.btn_request);
        cancel = findViewById(R.id.btn_cancel);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();





        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myamount = amount.getText().toString().trim();

                String myperiod = period.getText().toString().trim();
                String mytype = type.getText().toString().trim();
                if (TextUtils.isEmpty(myamount)){

                }

                else if (TextUtils.isEmpty(myperiod)){

                }
                else if (TextUtils.isEmpty(mytype)){

                }
                else {
                    mAmount = Double.parseDouble(amount.getText().toString().trim());

                    mPeriod = Integer.parseInt(period.getText().toString().trim());

                   double simpelIntrest = (mAmount * mPeriod * 12 )/100;
                   mAmount = simpelIntrest + mAmount;
                    FirebaseUser user = mAuth.getCurrentUser();

                    String uid = user.getUid();

                    DocumentReference dbLoan = db.collection("Loan").document();
                    Loan loan = new Loan();
                    loan.setLoan_id(dbLoan.getId());
                    loan.setLoan_amount(mAmount);
                    loan.setLoan_period(mPeriod);
                    loan.setLoan_type(mytype);
                    loan.setLoan_account_id(uid);

                    dbLoan.set(loan).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RequestLoanActivity.this, "Loan Approved Will be sent to MPesa KSH " + mAmount , Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RequestLoanActivity.this, "Loan not approved please try again later", Toast.LENGTH_SHORT).show();
                        }
                    });


                    DocumentReference dbTransation = db.collection("Transaction").document();
                    Transaction transaction = new Transaction();
                    transaction.setTransaction_account_id(uid);
                    transaction.setTransaction_amount(simpelIntrest);
                    transaction.setTransaction_id(dbTransation.getId());
                    transaction.setTransaction_mode("Loan Mpesa");
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
        });
    }
}
