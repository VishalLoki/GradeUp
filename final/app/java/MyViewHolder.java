package com.example.gradeup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView semester,credit,gpa;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        semester = itemView.findViewById(R.id.semester);
        credit = itemView.findViewById(R.id.credit);
        gpa = itemView.findViewById(R.id.gpa);
    }

}
