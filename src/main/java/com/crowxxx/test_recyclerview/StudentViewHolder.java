package com.crowxxx.test_recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    TextView tv_sname;
    TextView tv_sid;
    TextView tv_bio;
    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_sname = itemView.findViewById(R.id.tv_sname);
        tv_sid = itemView.findViewById(R.id.tv_sid);
        tv_bio = itemView.findViewById(R.id.tv_bio);
    }
}
