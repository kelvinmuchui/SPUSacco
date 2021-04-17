package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.materiallogin.model.Account;
import com.delaroystudios.materiallogin.model.Member;
import com.delaroystudios.materiallogin.model.Savings;
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

public class SavingActivity extends AppCompatActivity {

    TextView mUsername, mAmount;
    Button mSavings;


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        mUsername = findViewById(R.id.saving_userName);
        mAmount = findViewById(R.id.saving_amount);
        mSavings = findViewById(R.id.btn_savings);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();


       db = FirebaseFirestore.getInstance();


        CollectionReference savingRef = db.collection("Savings");
        CollectionReference memberRef = db.collection("Member");

        Query savingQuery = savingRef.whereEqualTo("saving_id", userId);
        savingQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Savings savings = documentSnapshot.toObject(Savings.class);
                        double amount = savings.getSaving_totalamount();
                        mAmount.setText(String.valueOf(amount));
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
                        mUsername.setText( member.getMember_name());

                    }
                }
            }
        });


        mSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SavingActivity.this);
                builder.setTitle("Enter amount to Save");

                LinearLayout linearLayout = new LinearLayout(SavingActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10,10,10,10);

                final EditText editText = new EditText(SavingActivity.this);
                editText.setHint("Enter Amount");
                linearLayout.addView(editText);

                builder.setView(linearLayout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String amount = editText.getText().toString().trim();
                        final double newAmount = Double.parseDouble(amount);

                        if (!TextUtils.isEmpty(amount)){
                            final DocumentReference documentReference2 = fStore.collection("Savings").document();
                            documentReference2.addSnapshotListener(SavingActivity.this, new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                    if (documentSnapshot.exists()){
                                        final double damount = Double.parseDouble(amount);
                                        Savings savings = documentSnapshot.toObject(Savings.class);
                                        savings.getSaving_totalamount();

                                        double dbAmount =savings.getSaving_totalamount();



                                            final double updateAmount = dbAmount + damount;


                                            db.collection("Savings").document(fAuth.getUid())
                                                    .update(
                                                            "Savings", updateAmount
                                                    )
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(SavingActivity.this, "You have Saved "+damount +"your balance is" + updateAmount, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });




                                    }
                                }
                            });


                        }else{
                            Toast.makeText(SavingActivity.this, "Please enter amount to Save", Toast.LENGTH_SHORT).show();
                        }
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
}
