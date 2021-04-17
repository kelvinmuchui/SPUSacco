package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.delaroystudios.materiallogin.model.Account;
import com.delaroystudios.materiallogin.model.Member;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class AccountDetailsActivity extends AppCompatActivity {

    private TextView memberName,memberPhone,accountNo, amount;
    private Button btnDeposite, btnWithdraw,btnEdit;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        memberName =findViewById(R.id.memberName);
        memberPhone = findViewById(R.id.memberAccountNo);
        accountNo = findViewById(R.id.memberAccountNo);
        amount=findViewById(R.id.Amount);

        btnDeposite = findViewById(R.id.btn_deposit);
        btnWithdraw = findViewById(R.id.btn_withdraw);
        btnEdit = findViewById(R.id.btn_editDetails);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference memberRef = db.collection("Member");
        CollectionReference accRef = db.collection("Account");


        Query accQuery =accRef
                .whereEqualTo("account_id", userId);
        accQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Account account = documentSnapshot.toObject(Account.class);
                        accountNo.setText(account.getAccount_no());
                        String myAmount = String.valueOf(account.getAccount_amountbal());
                        amount.setText(myAmount);

                    }
                }
            }
        });
        Query memberQuery = memberRef
                .whereEqualTo("member_account_id", userId);

        memberQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Member member = documentSnapshot.toObject(Member.class);
                       memberName.setText( member.getMember_name());
                       memberPhone.setText(member.getMember_contact());
                    }
                }
            }
        });

//
    }
}
