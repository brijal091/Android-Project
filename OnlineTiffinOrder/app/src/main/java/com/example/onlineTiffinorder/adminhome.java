package com.example.onlineTiffinorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.onlineTiffinorder.storage.sareprefrencelogin;

public class adminhome extends AppCompatActivity {
CardView viewuser,vieworder,editmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        viewuser = findViewById(R.id.viewuser);
        vieworder = findViewById(R.id.practiceCard1);
//        editmenu = findViewById(R.id.contributeCard);


        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminhome.this, Activity_viewuser.class);
                startActivity(intent);
            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminhome.this, showorderaddmin.class);
                startActivity(intent);
            }
        });

//        editmenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent k = new Intent(adminhome.this,Activity_editmenu.class);
////                    startActivity(k);
//            }
//        });
    }


    public void logout(View view) {
        sareprefrencelogin.getInstance(adminhome.this).clear();
        SharedPreferences role;
        SharedPreferences.Editor myEdit;
        role = getSharedPreferences("role",MODE_PRIVATE);
        myEdit = role.edit();
        myEdit.putString("type","nothing");
        myEdit.commit();
        Intent i=new Intent(adminhome.this, Loginpage.class);
        startActivity(i);
        finish();
    }
}