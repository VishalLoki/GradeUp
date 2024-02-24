package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/*public class sem_main extends AppCompatActivity {
    public List<Item> items = new ArrayList<Item>();
    public RecyclerView recyclerView;
    MyAdapter myAdapter;
    Item item;
    ImageButton plusbtn;
    String sem;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    TextView title;
    ImageView home,back;
    Dialog dialog;
    Button can_btn,del_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_sem_main);
        sem = getIntent().getStringExtra("sem");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users").child(encodedEmail).child(sem).child("SUBJECT");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    String subjectName = snapshot1.getKey();
                    String credit = snapshot1.child("scredit").getValue(String.class);
                    String grade = snapshot1.child("sgrade").getValue(String.class);

                    item = new Item(subjectName, credit, grade);
                    items.add(item);
                }

                myAdapter = new MyAdapter(sem_main.this, items,new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showDialog(position);
                    }
                });
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        plusbtn = findViewById(R.id.plus_btn);
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sem_main.this,plus_sem.class);
                intent.putExtra("sem",sem);
                startActivity(intent);
                finish();
            }
        });
        title = findViewById(R.id.title);
        title.setText(Change(sem));
        home = findViewById(R.id.home);
        back = findViewById(R.id.back);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sem_main.this,MainActivity.class);
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
    public void onItemClick(int position) {
        showDialog(position);
    }
    public void showCustomDialog(final int position) {
        dialog = new Dialog(sem_main.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);

        can_btn = dialog.findViewById(R.id.can_button);
        del_btn = dialog.findViewById(R.id.del_button);

        can_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectKey = items.get(position).getSemester();
                databaseReference.child("SUBJECT").child(subjectKey).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(sem_main.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(sem_main.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Remove the item from the list and notify the adapter
                items.remove(position);
                myAdapter.notifyItemRemoved(position);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public String Change(String s){
        if(s.charAt(3)=='1'){
            return "Semester 1";
        }else if(s.charAt(3)=='2'){
            return "Semester 2";
        }else if(s.charAt(3)=='3'){
            return "Semester 3";
        }else if(s.charAt(3)=='4'){
            return "Semester 4";
        }else if(s.charAt(3)=='5'){
            return "Semester 5";
        }else if(s.charAt(3)=='6'){
            return "Semester 6";
        }else if(s.charAt(3)=='7'){
            return "Semester 7";
        }
        return "Semester 8";
    }
}*/
public class sem_main extends AppCompatActivity {
    public List<Item> items = new ArrayList<Item>();
    public RecyclerView recyclerView;
    MyAdapter myAdapter;
    Item item;
    ImageButton plusbtn;
    String sem;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    TextView title;
    ImageView home, back;
    Dialog dialog;
    Button can_btn, del_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getColor(R.color.l_blue));
        getWindow().setStatusBarColor(getColor(R.color.l_blue));
        setContentView(R.layout.activity_sem_main);
        sem = getIntent().getStringExtra("sem");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());
        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users").child(encodedEmail).child(sem).child("SUBJECT");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String subjectName = snapshot1.getKey();
                    String credit = snapshot1.child("scredit").getValue(String.class);
                    String grade = snapshot1.child("sgrade").getValue(String.class);
                    item = new Item(subjectName, credit, grade);
                    items.add(item);
                }
                myAdapter = new MyAdapter(sem_main.this, items, new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        showCustomDialog(position);
                    }
                });
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
        plusbtn = findViewById(R.id.plus_btn);
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sem_main.this, plus_sem.class);
                intent.putExtra("sem", sem);
                startActivity(intent);
                finish();
            }
        });
        title = findViewById(R.id.title);
        title.setText(Change(sem));
        home = findViewById(R.id.home);
        back = findViewById(R.id.back);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sem_main.this, MainActivity.class);
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

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showCustomDialog(final int position) {
        dialog = new Dialog(sem_main.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);

        can_btn = dialog.findViewById(R.id.can_button);
        del_btn = dialog.findViewById(R.id.del_button);

        can_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectKey = items.get(position).getSemester();

                databaseReference.child(subjectKey).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(sem_main.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(sem_main.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                            }
                        });
                items.remove(position);
                myAdapter.notifyItemRemoved(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public String Change(String s) {
        if (s.charAt(3) == '1') {
            return "Semester 1";
        } else if (s.charAt(3) == '2') {
            return "Semester 2";
        } else if (s.charAt(3) == '3') {
            return "Semester 3";
        } else if (s.charAt(3) == '4') {
            return "Semester 4";
        } else if (s.charAt(3) == '5') {
            return "Semester 5";
        } else if (s.charAt(3) == '6') {
            return "Semester 6";
        } else if (s.charAt(3) == '7') {
            return "Semester 7";
        }
        return "Semester 8";
    }
}