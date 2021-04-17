package com.delaroystudios.materiallogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.delaroystudios.materiallogin.model.Account;
import com.delaroystudios.materiallogin.model.Login;
import com.delaroystudios.materiallogin.model.Member;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignupActivity";
    ProgressDialog pd;


    String memberid;
    String accountId;
    String uid;


    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_address) EditText _addressText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_mobile) EditText _mobileText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();



        pd = new ProgressDialog(this);
        pd.setMessage("Registering User...");
        _signupButton.setOnClickListener(new View.OnClickListener() {
            String emailtx = _emailText.getText().toString();
            final String passwordtx = _passwordText.getText().toString();

            @Override
            public void onClick(View v) {
//                signup();
                String email = _emailText.getText().toString().trim();
                String password = _passwordText.getText().toString().trim();
                Toast.makeText(SignupActivity.this,"" +email, Toast.LENGTH_SHORT).show();

                //validate
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //set error and focuss
                    _emailText.setText("Invalid Email");
                    _emailText.setFocusable(true);

                }else if(password.length()<6){

                    //set error and focuss
                    _passwordText.setText("Password length at least 6 characters");
                    _passwordText.setFocusable(true);
                }else{
                    registerUser(email,password);
                }

            }
        });





        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private void registerUser(String email, final String password) {
        final String name = _nameText.getText().toString();
        final String address = _addressText.getText().toString();

        final String mobile = _mobileText.getText().toString();
        final String passwordtx = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        final String accountId = UUID.randomUUID().toString();
        final String memberID = UUID.randomUUID().toString();

        String start = "KE";
        Random value = new Random();

        //Generate two values to append to 'BE'
        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for(int i =0; i < 12;i++)
        {
            if(count == 4)
            {
                start += " ";
                count =0;
            }
            else
                n = value.nextInt(10);
            start += Integer.toString(n);
            count++;

        }

        final String account_no = start;

        final String finalStart = start;
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){


                            //Get user email and uid from auth

                            uid = mAuth.getUid();

                            createAccountdata(0.0,"Savings", account_no,uid);
                            addMemberData(name,mobile);
                            addLoginData(name,password);



                            Toast.makeText(SignupActivity.this, "Registered....\n", Toast.LENGTH_SHORT).show();
                            Intent Signupintent =new Intent(SignupActivity.this, CustomerHomeActivity.class);
                            startActivity(Signupintent);
                            finish();
                        }else{
                            Toast.makeText(SignupActivity.this, "Authenitaction failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void addLoginData(String username, String password){
        DocumentReference dbLogin = db.collection("Login").document();

        Login login = new Login();
        login.setLogin_id(dbLogin.getId());
        login.setLogin_username(username);
        login.setLogin_password(password);
        login.setLogin_member_id(memberid);

        dbLogin.set(login).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SignupActivity.this, "User created", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addMemberData( String member_name,String member_contact){
        DocumentReference dbMember = db.collection("Member").document();

        memberid = dbMember.getId();

        FirebaseUser user = mAuth.getCurrentUser();

        String uid = user.getUid();
        Member member = new Member();
        member.setMember_id(dbMember.getId());
        member.setMember_name(member_name);
        member.setMember_account_id(uid);
        member.setMember_contact(member_contact);

        dbMember.set(member).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void avoid) {
                Toast.makeText(SignupActivity.this, "Member Created", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
            }
        });





    }


    private void createAccountdata( Double account_amountbal, String account_type, String account_no ,String id){


        DocumentReference dbAccount = db.collection("Account").document(uid);
        accountId =dbAccount.getId();
        Account account = new Account();
        FirebaseUser user = mAuth.getCurrentUser();

        String uid = user.getUid();

        account.setAccount_id(uid);
        account.setAccount_type(account_type);
        account.setAccount_amountbal(account_amountbal);
        account.setAccount_no(account_no);

        dbAccount.set(account).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}