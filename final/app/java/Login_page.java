package com.example.gradeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Base64;

public class Login_page extends AppCompatActivity {
    EditText email,pass,pass1;
    Button loginbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView acc,forgot;
    String[] item = {"Civil Engineering","Computer Science Engineering","Electronics and Communication Engineering","Electrical and Electronics Engineering","Electronics and Instrumentation Engineering","Industrial Biotechnology","Information Technology","Mechanical Engineering","Production Engineering"};
    String[] item1 = {"Regulation 18","Regulation 22"};
    EditText name,roll,mobile;
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    ArrayAdapter<String> adapterItems,adapterItems1;
    FirebaseDatabase database;
    DatabaseReference reference;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Login_page.this, MainActivity.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        roll = findViewById(R.id.roll);
        mobile = findViewById(R.id.mobile);
        pass = findViewById(R.id.password);
        pass1 = findViewById(R.id.password1);
        loginbtn = findViewById(R.id.loginbtn);
        forgot = findViewById(R.id.forgotpass);
        acc = findViewById(R.id.acc);
        progressBar = findViewById(R.id.progress_Bar);

        autoCompleteTextView = findViewById(R.id.Auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Login_page.this,"Dept:"+item,Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView1 = findViewById(R.id.Auto_complete_txt1);
        adapterItems1 = new ArrayAdapter<String>(this,R.layout.list_item,item1);
        autoCompleteTextView1.setAdapter(adapterItems1);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item1=parent.getItemAtPosition(position).toString();
                Toast.makeText(Login_page.this,"Reg:"+item1,Toast.LENGTH_SHORT).show();
            }
        });
        name = findViewById(R.id.name);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String Email = email.getText().toString();
                String full_name = name.getText().toString();
                String Roll = roll.getText().toString();
                String Mobile = mobile.getText().toString();
                String dept = autoCompleteTextView.getText().toString();
                String reg = autoCompleteTextView1.getText().toString();
                String Pass = pass.getText().toString();
                String Pass1 = pass.getText().toString();
                if(!Pass.equals(Pass1)){
                    Toast.makeText(Login_page.this,"Confirm your password Correctly",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(Email) && TextUtils.isEmpty(full_name) && TextUtils.isEmpty(dept) && TextUtils.isEmpty(reg) && TextUtils.isEmpty(Pass) && TextUtils.isEmpty(Pass1) && TextUtils.isEmpty(Pass) && TextUtils.isEmpty(Pass1)){
                    Toast.makeText(Login_page.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        HelperClass helperClass = new HelperClass(Email,full_name,dept,reg,Roll,Mobile);
                                        String encodedEmail = Base64.getEncoder().encodeToString(Email.getBytes());
                                        reference.child(encodedEmail).child("details").setValue(helperClass);
                                        for(int i=1;i<=8;i++){
                                            reference.child(encodedEmail).child("sem"+i).child("GPA").child("gpa").setValue("0.00");
                                        }
                                        reference.child(encodedEmail).child("CGPA").child("cgpa").setValue("0.00");
                                        Toast.makeText(Login_page.this, "Account Created Registration Successful",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login_page.this, Login_page2.class));
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login_page.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_page.this,Login_page2.class));
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_page.this,forgot.class));
            }
        });
    }
}