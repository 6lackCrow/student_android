package com.crowxxx.test_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView.Adapter adapter;
    List<Student> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );

        adapter = new StudentAdapter(this,dataList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        loadData();

    }

    void loadData(){
        //异步请求
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder()
                .url("http://stu.crowxx.xyz/study")
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okhttp_error",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("okhttp_success",json);
                JSONObject jsonObject = JSON.parseObject(json);
                JSONArray content = jsonObject.getJSONArray("content");
                List<Student> students = new ArrayList<>();

                for (int i = 0; i < content.size(); i++) {
                    JSONObject jsonObject1 = content.getJSONObject(i);
                    Student student = jsonObject1.toJavaObject(Student.class);
                    students.add(student);
                }

                dataList.clear();
                dataList.addAll(students);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

                Log.e("TAG", "onResponse: " + students.size() );
            }
        });
    }
}
