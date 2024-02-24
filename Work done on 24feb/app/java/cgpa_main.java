package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class cgpa_main extends AppCompatActivity {
    List<Item1> items= new ArrayList<>();
    Item1 item1;
    FirebaseAuth mAuth;
    RecyclerView recyclerView1;
    FirebaseDatabase databaseReference;
    MyAdapter1 myAdapter1;
    ImageView home,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_cgpa_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
        recyclerView1 = findViewById(R.id.recycler_View);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        items.clear();
        for(int i=1;i<9;i++){
            final int j=i;
            firebaseDatabase.getReference("users").child(encodedEmail).child("sem"+i).child("GPA").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String gpa = snapshot.child("gpa").getValue(String.class);
                    item1 = new Item1("Semester"+j,gpa);
                    items.add(item1);
                    if (j == 8) {
                        myAdapter1 = new MyAdapter1(cgpa_main.this, items);
                        recyclerView1.setAdapter(myAdapter1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        myAdapter1 = new MyAdapter1(cgpa_main.this, items);
        recyclerView1.setAdapter(myAdapter1);
        home = findViewById(R.id.home);
        back = findViewById(R.id.back);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cgpa_main.this,MainActivity.class);
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
