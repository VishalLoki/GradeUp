package com.example.gradeup;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder1 extends RecyclerView.ViewHolder{
    TextView semester,gpa;
    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        semester = itemView.findViewById(R.id.semester);
        gpa = itemView.findViewById(R.id.gpa);
    }
}
