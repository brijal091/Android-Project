package com.example.onlineTiffinorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.CommanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrationpage extends AppCompatActivity {
    private TextView gotoLogin;
    EditText fname,lname,mobno,email,password,cpassword;
    Button conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);



        gotoLogin = (TextView) findViewById(R.id.gotoLoginr);
        fname = (EditText) findViewById(R.id.inputfname);
        lname = (EditText) findViewById(R.id.inputlname);
        mobno = (EditText) findViewById(R.id.inputmobile);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
        cpassword = (EditText) findViewById(R.id.inputConpassword);
        conf = (Button) findViewById(R.id.btnRegister);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adduser();
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginpage();
            }
        });
    }

    public void openLoginpage(){

        Intent intent = new Intent(this, Loginpage.class);
        startActivity(intent);
        finish();
    }

    private void adduser() {
        String sfname,slname,smobno,semail,spassword,scpassword;
        sfname=fname.getText().toString();
        slname=lname.getText().toString();
        smobno=mobno.getText().toString();
        semail=email.getText().toString();
        spassword=password.getText().toString();
        scpassword=cpassword.getText().toString();
        Log.e("TAG", "adduser: "+sfname+slname+smobno+semail+spassword+scpassword );
        //Toast.makeText(this, sfname+slname+smobno+semail+spassword+scpassword, Toast.LENGTH_SHORT).show();
        if(spassword.equals(scpassword)) {

            if (!sfname.isEmpty() && !slname.isEmpty() && !smobno.isEmpty() &&
                    !semail.isEmpty() && !spassword.isEmpty()) {

                Api api = ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call = api.createUser("adduser", sfname, slname, smobno, semail, spassword);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(registrationpage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registrationpage.this, Loginpage.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(registrationpage.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(registrationpage.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });





            } else {
                Toast.makeText(this, "enter all field", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "password and confirm password not match", Toast.LENGTH_SHORT).show();

        }


    }
}