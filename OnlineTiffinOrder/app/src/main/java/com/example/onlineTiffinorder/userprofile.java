package com.example.onlineTiffinorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.storage.sareprefrencelogin;

public class userprofile extends AppCompatActivity {
TextView fname,lname,email,mobno, welcome_User;
Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

            fname=findViewById(R.id.textfnamep);
            lname=findViewById(R.id.textlnamep);
            mobno=findViewById(R.id.textmobp);
            email=findViewById(R.id.textemailp);
            logout=findViewById(R.id.btnlogout);
            welcome_User = findViewById(R.id.user);

            if(sareprefrencelogin.getInstance(this).islogin())
            {
                User user= sareprefrencelogin.getInstance(userprofile.this).getuser();
                fname.setText(user.getFname());
                lname.setText(user.getLname());
                mobno.setText(user.getMobno());
                email.setText(user.getEmail());
                welcome_User.setText("Hi! " + user.getFname());

            }

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences role;
                    SharedPreferences.Editor myEdit;
                    role = getSharedPreferences("role",MODE_PRIVATE);
                    myEdit = role.edit();
                    myEdit.putString("type","nothing");
                    myEdit.commit();
                    sareprefrencelogin.getInstance(userprofile.this).clear();
                    Intent i=new Intent(userprofile.this, Loginpage.class);
                    startActivity(i);
                    finish();
                }
            });

    }
}