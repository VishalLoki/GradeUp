package com.example.gradeup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1> {
    Context context;
    List<Item1> items;

    public MyAdapter1(Context context, List<Item1> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_view1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        holder.semester.setText(items.get(position).getSem());
        holder.gpa.setText(items.get(position).getGpa());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), sem_main.class);
                String sam = items.get(position).getSem();
                intent.putExtra("sem","sem"+sam.charAt(sam.length()-1));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
