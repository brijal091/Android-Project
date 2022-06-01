package com.example.onlineTiffinorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.api.responce.breakfast_get_set;
import com.example.onlineTiffinorder.api.responce.breakfastadapter;
import com.example.onlineTiffinorder.api.responce.breakresponce;
import com.example.onlineTiffinorder.api.responce.dinner_get_set;
import com.example.onlineTiffinorder.api.responce.dinnerresponce;
import com.example.onlineTiffinorder.api.responce.lunch_get_set;
import com.example.onlineTiffinorder.api.responce.lunchadapter;
import com.example.onlineTiffinorder.api.responce.lunchresponce;
import com.example.onlineTiffinorder.api.responce.order_adapter;
import com.example.onlineTiffinorder.storage.sareprefrencelogin;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class showorder extends AppCompatActivity {
    Button login;
    Button order_lunch;
    RadioButton rdlunch;
    RadioButton rdbfast;
    RadioButton rddinner;
    ImageButton imageButton;
    String userid,condition;
    lunchadapter lunchad;
    order_adapter dinnerad;
    breakfastadapter breakfast;

    List<dinner_get_set> di;
    List<breakfast_get_set> bi;
    List<lunch_get_set> li;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showorder);
        recyclerView=(RecyclerView) findViewById(R.id.order_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(sareprefrencelogin.getInstance(this).islogin())
        {
            User user= sareprefrencelogin.getInstance(showorder.this).getuser();
            userid=user.getId()+"";

        }

        condition="custid='"+userid+"'";

        rdlunch = findViewById(R.id.rdbtnlunchshow);
        rdbfast = findViewById(R.id.rdbtnbreakfastshow);
        rddinner = findViewById(R.id.rdbtndinershow);
        imageButton = findViewById(R.id.imageButtonshow);

        rdbfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdbfast.isChecked()){
breakfastload();
                }
                else{

                }
            }
        });
        rdlunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdlunch.isChecked()){
lunchload();
                }
                else{

                }
            }
        });
        rddinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rddinner.isChecked()){
                dinnerload();
                }
                else {

                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openuserprofile();
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloginpage();
            }
        });




    }

    public void openuserprofile(){
        Intent intent = new Intent(this, userprofile.class);
        startActivity(intent);
    }

    public void openloginpage(){
        Intent intent = new Intent(this, Loginpage.class);
        startActivity(intent);
    }

    public void breakfastload(){

        Api api= ApiClient.getClient().create(Api.class);
        Call<breakresponce> call= api.getbreakfast("getbreakfast",condition);
        call.enqueue(new Callback<breakresponce>() {
            @Override
            public void onResponse(Call<breakresponce> call, Response<breakresponce> response) {
                if (response.body().getSuccess()==200) {
                    bi=response.body().getDe();
                    Collections.reverse(bi);
                    breakfast=new breakfastadapter(showorder.this,bi);

                    recyclerView.setAdapter(breakfast);

                }
                else {
                    Toast.makeText(showorder.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<breakresponce> call, Throwable t) {
                Toast.makeText(showorder.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });


    }




    public void dinnerload(){

        Api api= ApiClient.getClient().create(Api.class);
        Call<dinnerresponce> call= api.getdinner("getbreakfast",condition);
        call.enqueue(new Callback<dinnerresponce>() {
            @Override
            public void onResponse(Call<dinnerresponce> call, Response<dinnerresponce> response) {
                if (response.body().getSuccess()==200) {
                    di=response.body().getDe();
                    Collections.reverse(di);
                    dinnerad=new order_adapter(showorder.this,di);

                    recyclerView.setAdapter(dinnerad);

                }
                else {
                    Toast.makeText(showorder.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<dinnerresponce> call, Throwable t) {
                Toast.makeText(showorder.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void lunchload(){

        Api api= ApiClient.getClient().create(Api.class);
        Call<lunchresponce> call= api.getlunch("getbreakfast",condition);
        call.enqueue(new Callback<lunchresponce>() {
            @Override
            public void onResponse(Call<lunchresponce> call, Response<lunchresponce> response) {
                if (response.body().getSuccess()==200) {
                    li=response.body().getDe();
                    Collections.reverse(li);
                    lunchad=new lunchadapter(showorder.this,li);

                    recyclerView.setAdapter(lunchad);

                }
                else {
                    Toast.makeText(showorder.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<lunchresponce> call, Throwable t) {
                Toast.makeText(showorder.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });


    }




}