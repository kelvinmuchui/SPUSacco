package com.delaroystudios.materiallogin.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.delaroystudios.materiallogin.AccountDetailsActivity;
import com.delaroystudios.materiallogin.InvestimentActivity;
import com.delaroystudios.materiallogin.R;
import com.delaroystudios.materiallogin.SavingActivity;
import com.delaroystudios.materiallogin.model.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    CardView account , deposit, withdraw, invest, save;

    ProgressDialog pd;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private FirebaseFirestore db;

    double myAmount;



    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_account, container, false);
        account = view.findViewById(R.id.account);
        deposit = view.findViewById(R.id.deposit);
        withdraw =view.findViewById(R.id.withdraw);
        invest =view.findViewById(R.id.invest);
        save = view.findViewById(R.id.save);
        db = FirebaseFirestore.getInstance();



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        //userId = fAuth.getCurrentUser().getUid();
        pd = new ProgressDialog(getActivity());

        final Intent intent = new Intent();




//        final DocumentReference documentReference2 = fStore.collection("Account").document(userId);

//        DocumentReference docRef = db.collection("Account").document(fAuth.getUid());
//
//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Account account = documentSnapshot.toObject(Account.class);
//                myAmount=account.getAccount_amountbal();
//            }
//        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(getActivity(), AccountDetailsActivity.class);
                startActivity(account);
            }
        });

        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), InvestimentActivity.class);
                startActivity(intent1);
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Enter amount to deposit");

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10,10,10,10);

                final EditText editText = new EditText(getActivity());
                editText.setHint("Enter Amount");
                linearLayout.addView(editText);

                builder.setView(linearLayout);
                builder.setPositiveButton("Deposit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String amount = editText.getText().toString().trim();
//                        final double newAmount = Double.parseDouble(amount);
//                        DocumentReference documentReference2 = fStore.collection("Account").document(fAuth.getUid());
//                        documentReference2.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                                if (documentSnapshot.exists()) {
//
//
//
//                                    myAmount = documentSnapshot.getDouble("account_amountbal");
//
//
//
//                                }
//                            }
//                        });

                        if (!TextUtils.isEmpty(amount)) {

                            final double damount = Double.parseDouble(amount);


                            final double updateAmount = myAmount + damount;

                            db.collection("Account").document(fAuth.getUid())
                                    .update(
                                            "account_amountbal", updateAmount
                                    )
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getActivity(), "You have deposited" + updateAmount, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        } else {
                            Toast.makeText(getActivity(), "Please enter amount to deposit", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }


                });
                builder.create().show();
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Enter amount to Withdraw");

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10,10,10,10);

                final EditText editText = new EditText(getActivity());
                editText.setHint("Enter Amount");
                linearLayout.addView(editText);

                builder.setView(linearLayout);
                builder.setPositiveButton("Withdraw", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String amount = editText.getText().toString().trim();
                        final double newAmount = Double.parseDouble(amount);

                        if (!TextUtils.isEmpty(amount)){
                            final DocumentReference documentReference2 = fStore.collection("Account").document();
                            documentReference2.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                    if (documentSnapshot.exists()){
                                        final double damount = Double.parseDouble(amount);
                                        Account account = documentSnapshot.toObject(Account.class);
                                        account.getAccount_amountbal();

                                        double dbAmount = account.getAccount_amountbal();


                                        if(damount > myAmount){
                                            Toast.makeText(getActivity(), "Your have insufitient fund please deposit your balance is" + dbAmount, Toast.LENGTH_SHORT).show();
                                        }else{


                                            final double updateAmount = myAmount - damount;


                                            db.collection("Account").document(fAuth.getUid())
                                                    .update(
                                                            "account_amountbal", updateAmount
                                                    )
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getActivity(), "You have Withdrawn "+damount +"your balance is" + updateAmount, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }



                                    }
                                }
                            });


                        }else{
                            Toast.makeText(getActivity(), "Please enter amount to Withdraw", Toast.LENGTH_SHORT).show();
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


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SavingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void getAmount() {
    }

}
