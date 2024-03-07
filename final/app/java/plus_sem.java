package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Base64;

public class plus_sem extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    String sem;
    Button add_btn;
    EditText sname,scredit,sgrade;
    ImageView home,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_plus_sem);
        sem = getIntent().getStringExtra("sem");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
        databaseReference = firebaseDatabase.getReference("users/"+encodedEmail+"/"+sem);
        sname = findViewById(R.id.sname);
        scredit = findViewById(R.id.scredit);
        sgrade = findViewById(R.id.sgrade);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sname = sname.getText().toString();
                String Scredit = scredit.getText().toString();
                String Sgrade = sgrade.getText().toString();
                if(TextUtils.isEmpty(Sname) || TextUtils.isEmpty(Scredit) || TextUtils.isEmpty(Sgrade)){
                    Toast.makeText(plus_sem.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Double.parseDouble(Scredit)<0.0 || Double.parseDouble(Scredit)>=5.0 ){
                    Toast.makeText(plus_sem.this,"Invalid Credit",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Double.parseDouble(Sgrade)<0.0 || (Double.parseDouble(Sgrade)<5.0 && Double.parseDouble(Sgrade)!=0.0) || Double.parseDouble(Sgrade)>10.0){
                    Toast.makeText(plus_sem.this,"Invalid Grade",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Sem_Helper_Class semHelperClass = new Sem_Helper_Class(Sname,Scredit,Sgrade);
                    databaseReference.child("SUBJECT").child(Sname).setValue(semHelperClass);
                    Toast.makeText(plus_sem.this, "Subject : "+Sname+" Added",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(plus_sem.this, sem_main.class);
                    intent.putExtra("sem",sem);
                    startActivity(intent);
                    finish();
                }
            }
        });


        home = findViewById(R.id.home);
        back = findViewById(R.id.back);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(plus_sem.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}