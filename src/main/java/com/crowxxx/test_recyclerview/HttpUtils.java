package com.crowxxx.test_recyclerview;

import android.util.Log;

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

public class HttpUtils {
    public static void getData(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okhttp_error",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JSONObject jsonObject = JSONObject.parseObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("content");
                List<Student> list = new  ArrayList<>();
                for (int i = 0;i < jsonArray.size();i++){
                    list.add(jsonArray.getJSONObject(i).toJavaObject(Student.class));
                }

            }
        });
    }
}
