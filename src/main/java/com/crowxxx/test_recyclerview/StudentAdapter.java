package com.crowxxx.test_recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    Context context;
    List<Student> dataList;
    public StudentAdapter(Context context,List<Student> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_view,null);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = dataList.get(position);
        holder.tv_sname.setText(student.getSname());
        holder.tv_sid.setText(student.getSid());
        holder.tv_bio.setText(student.getBio());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
