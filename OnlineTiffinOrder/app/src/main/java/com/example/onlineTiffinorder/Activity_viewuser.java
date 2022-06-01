package com.example.onlineTiffinorder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.api.responce.breakfastadapter;
import com.example.onlineTiffinorder.api.responce.breakresponce;
import com.example.onlineTiffinorder.api.responce.customeradapte;
import com.example.onlineTiffinorder.api.responce.dinner_get_set;
import com.example.onlineTiffinorder.api.responce.userresponce;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_viewuser extends AppCompatActivity {
    RecyclerView recyclerView;
    customeradapte ct;
    List<User> li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        recyclerView=(RecyclerView) findViewById(R.id.recycle_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loaduser();
    }

    public void loaduser(){
        Api api= ApiClient.getClient().create(Api.class);
        Call<userresponce> call= api.getuser("getuser");
        call.enqueue(new Callback<userresponce>() {
            @Override
            public void onResponse(Call<userresponce> call, Response<userresponce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    ct =new customeradapte(Activity_viewuser.this,li);
                    recyclerView.setAdapter(ct);
                }
                else {
                    Toast.makeText(Activity_viewuser.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<userresponce> call, Throwable t) {
                Toast.makeText(Activity_viewuser.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}