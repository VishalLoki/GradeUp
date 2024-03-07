package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Base64;

public class logout extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button button;
    FirebaseUser user;
    TextView Name,Roll,Reg,Dept,Mobile,Email;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_logout);

        Name = findViewById(R.id.name);
        Roll = findViewById(R.id.rollno);
        Reg = findViewById(R.id.reg);
        Dept = findViewById(R.id.dept);
        Mobile = findViewById(R.id.phone);
        Email = findViewById(R.id.email);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.button);
        user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(logout.this,Login_page2.class));
            finish();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(logout.this,Login_page2.class));
                finish();
            }
        });
        String email = user.getEmail();
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("users/"+encodedEmail+"/details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                String roll = snapshot.child("roll").getValue(String.class);
                String reg = snapshot.child("reg").getValue(String.class);
                String dept = snapshot.child("dept").getValue(String.class);
                String mobile = snapshot.child("mobile").getValue(String.class);
                Name.setText(name);
                Roll.setText("RNO:"+roll);
                Reg.setText(reg);
                Dept.setText(dept);
                Mobile.setText("+91 "+mobile);
                Email.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}