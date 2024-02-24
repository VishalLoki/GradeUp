package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import org.w3c.dom.Text;

import java.util.Base64;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    Button cgpa,sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;
    ImageView refresh_btn;
    TextView cgpa_num;
    TextView cgpa_quote;
    DatabaseReference databaseReference,databaseReference1;
    Double totalCredits = 0.0;
    Double totalWeightedGPA = 0.0;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String email = user.getEmail();
    String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_main);
        cgpa = findViewById(R.id.cgpa_button);
        sem1 = findViewById(R.id.sem1_button);
        sem2 = findViewById(R.id.sem2_button);
        sem3 = findViewById(R.id.sem3_button);
        sem4 = findViewById(R.id.sem4_button);
        sem5 = findViewById(R.id.sem5_button);
        sem6 = findViewById(R.id.sem6_button);
        sem7 = findViewById(R.id.sem7_button);
        sem8 = findViewById(R.id.sem8_button);
        refresh_btn = findViewById(R.id.refresh_btn);

        cgpa_num = findViewById(R.id.cgpa_num);
        cgpa_quote = findViewById(R.id.cgpa_quote);

        Set_cgquote();

        swipeRefreshLayout = findViewById(R.id.SwipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh_cg();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Refresh_cg();
            }
        });
        cgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, cgpa_main.class));
            }
        });
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem1");
                startActivity(intent);
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem2");
                startActivity(intent);
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem3");
                startActivity(intent);
            }
        });
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem4");
                startActivity(intent);
            }
        });
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem5");
                startActivity(intent);
            }
        });
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem6");
                startActivity(intent);
            }
        });
        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem7");
                startActivity(intent);
            }
        });
        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sem_main.class);
                intent.putExtra("sem","sem8");
                startActivity(intent);
            }
        });
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, logout.class));
            }
        });
    }
    public String Quote(String s){
        double i = Double.parseDouble(s);
        if(i<7.0){
            return "Oops! Let's turn those grades frowns upside down!";
        }else if(i>=7.0 && i<8.0){
            return "Hit snooze on study mode? Time to wake up!";
        }else if(i>=8.0 && i<9.0) {
            return  "You're on fire! Keep those brain cells sizzling!";
        }else if(i>=9.0 && i<9.5) {
            return  "You're on fire! Keep those brain cells sizzling!";
        }
        return "Perfection unlocked! You're a study superhero!";
    }
    public void Set_cgcgpa(){
        for(int i=1;i<9;i++){
            final int semesterNumber=i;
            firebaseDatabase.getReference("users/"+encodedEmail+"/sem"+i+"/SUBJECT").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Double semesterCredits = 0.0;
                    Double semesterWeightedGPA = 0.0;

                    // Calculate semester GPA
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String credit = dataSnapshot.child("scredit").getValue(String.class);
                        String grade = dataSnapshot.child("sgrade").getValue(String.class);
                        semesterCredits += Double.parseDouble(credit);
                        semesterWeightedGPA += Double.parseDouble(credit) * Double.parseDouble(grade);
                    }
                    if (semesterCredits > 0) {
                        Double semesterGPA = semesterWeightedGPA / semesterCredits;

                        firebaseDatabase.getReference("users/"+encodedEmail+"/sem"+semesterNumber+"/GPA").child("gpa").setValue(String.format("%.2f", semesterGPA));

                        totalCredits += semesterCredits;
                        totalWeightedGPA += semesterWeightedGPA;
                    }else{
                        firebaseDatabase.getReference("users/"+encodedEmail+"/sem"+semesterNumber+"/GPA").child("gpa").setValue("0.00");
                    }
                    if (semesterNumber == 8) {
                        Double cgpa = totalWeightedGPA / totalCredits;
                        firebaseDatabase.getReference("users/" + encodedEmail + "/CGPA").child("cgpa").setValue(String.format("%.2f", cgpa), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error == null) {
                                    // Update UI here with the CGPA
                                    Set_cgquote();
                                } else {
                                    // Handle error
                                }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public void Set_cgquote(){
        databaseReference = firebaseDatabase.getReference("users/"+encodedEmail+"/CGPA");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cgpa_n= snapshot.child("cgpa").getValue(String.class);
                cgpa_num.setText(cgpa_n);
                cgpa_quote.setText(Quote(cgpa_n));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(int i=1;i<9;i++){
            final int semesterNumber = i;
            firebaseDatabase.getReference("users/"+encodedEmail+"/sem"+i+"/GPA").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String gpa_n= snapshot.child("gpa").getValue(String.class);
                    TextView semesterGpaNum = findViewById(getResources().getIdentifier("sem" + semesterNumber + "_num", "id", getPackageName()));
                    TextView semesterGpaQuote = findViewById(getResources().getIdentifier("sem" + semesterNumber + "_quote", "id", getPackageName()));
                    semesterGpaNum.setText(gpa_n);
                    semesterGpaQuote.setText(Quote(gpa_n));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public void Refresh_cg(){
        totalCredits = 0.0;
        totalWeightedGPA = 0.0;
        Set_cgcgpa();
    }
}