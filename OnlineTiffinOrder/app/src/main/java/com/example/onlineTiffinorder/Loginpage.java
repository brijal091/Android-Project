package com.example.onlineTiffinorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.responce.loginresponce;
import com.example.onlineTiffinorder.storage.sareprefrencelogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginpage extends AppCompatActivity {

    private TextView gotoRegistration;
    EditText mobno,pass;
    Button login;
    Spinner spinner;
    SharedPreferences role;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        role = getSharedPreferences("role",MODE_PRIVATE);
        myEdit = role.edit();
        spinner = findViewById(R.id.spinerlogin);
         mobno = findViewById(R.id.inputEmaillogin);
         pass = findViewById(R.id.inputPasswordlogin);
         login = findViewById(R.id.btnLogin);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                Loginpage.this,
                R.layout.style_spinner,
                getResources().getStringArray(R.array.login)
        );

        adapter.setDropDownViewResource(R.layout.style_spinner_dropdown);
        spinner.setAdapter(adapter);


        gotoRegistration = (TextView) findViewById(R.id.gotoLogin);
        gotoRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openregistrationpage();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logininto();
            }
        });
    }

    public void openregistrationpage(){
        Intent intent = new Intent(this, registrationpage.class);
        startActivity(intent);
    }

    public  void  logininto(){
        String n=mobno.getText().toString();
        String p=pass.getText().toString();
        String role=spinner.getSelectedItem()+"";
        if(role.equals("User")) {
            Api api = ApiClient.getClient().create(Api.class);
            Call<loginresponce> call = api.login("loginmember", n, p);
            call.enqueue(new Callback<loginresponce>() {
                @Override
                public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                    if (response.body().getSuccess() == 405) {
                        loginresponce loginresponce = response.body();
                        sareprefrencelogin.getInstance(Loginpage.this).saveuser(loginresponce.getUser());
                        myEdit.putString("type","user");
                        myEdit.commit();
                        Intent i = new Intent(Loginpage.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        Toast.makeText(Loginpage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Loginpage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<loginresponce> call, Throwable t) {
                    Toast.makeText(Loginpage.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {

            Api api = ApiClient.getClient().create(Api.class);
            Call<loginresponce> call = api.adminlogin("loginmember", n, p);
            call.enqueue(new Callback<loginresponce>() {
                @Override
                public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                    if (response.body().getSuccess() == 405) {
                        loginresponce loginresponce = response.body();
                        sareprefrencelogin.getInstance(Loginpage.this).saveuser(loginresponce.getUser());

                        myEdit.putString("type","admin");
                        myEdit.commit();
                        Intent i = new Intent(Loginpage.this, adminhome.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        Toast.makeText(Loginpage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Loginpage.this, response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<loginresponce> call, Throwable t) {
                    Toast.makeText(Loginpage.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sareprefrencelogin.getInstance(this).islogin())
        {
            SharedPreferences sh1 = getSharedPreferences("role", MODE_PRIVATE);
            String type=sh1.getString("type","");
            Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();
            if(type.equals("admin")){
                Intent i = new Intent(Loginpage.this, adminhome.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
            else if(type.equals("user")) {
                Intent i = new Intent(Loginpage.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
            else {

            }

        }

    }
}