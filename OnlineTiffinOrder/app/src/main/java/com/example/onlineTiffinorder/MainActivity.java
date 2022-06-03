package com.example.onlineTiffinorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.CommanResponse;
import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.storage.sareprefrencelogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button order_lunch;
    RadioButton rdlunch;
    RadioButton rdbfast;
    RadioButton rddinner;
    ConstraintLayout forlunch;
    ConstraintLayout forbfast;
    ConstraintLayout fordinner;
    ImageButton imageButton;
    Button orderbreak,orderlunch,orderdinner;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(sareprefrencelogin.getInstance(this).islogin())
        {
            User user= sareprefrencelogin.getInstance(MainActivity.this).getuser();
            userid=user.getId()+"";

        }
        orderbreak = findViewById(R.id.order_bfast);
        orderlunch = findViewById(R.id.order_lunch);
        orderdinner = findViewById(R.id.order_dinner);
        rdlunch = findViewById(R.id.rdbtnlunch);
        forlunch =findViewById(R.id.lunchlayout);
        forlunch.setVisibility(View.GONE);
        rdbfast = findViewById(R.id.rdbtnbreakfast);
        forbfast = findViewById(R.id.breakfastlayout);
        forbfast.setVisibility(View.GONE);
        rddinner = findViewById(R.id.rdbtndiner);
        fordinner = findViewById(R.id.dinnerlayout);
        fordinner.setVisibility(View.GONE);
        imageButton = findViewById(R.id.imageButton);

        rdbfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdbfast.isChecked()){
                    forbfast.setVisibility(View.VISIBLE);
                }
                else{
                    forbfast.setVisibility(View.GONE);
                }
            }
        });
        rdlunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdlunch.isChecked()){
                    forlunch.setVisibility(View.VISIBLE);
                }
                else{
                    forlunch.setVisibility(View.GONE);
                }
            }
        });
        rddinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rddinner.isChecked()){
                    fordinner.setVisibility(View.VISIBLE);
                }
                else {
                    fordinner.setVisibility(View.GONE);
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openuserprofile();
            }
        });

        order_lunch = (Button) findViewById(R.id.order_lunch);
        order_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openorderpage1();
            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloginpage();
            }
        });



        orderbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addbreakfast();
            }
        });
        orderlunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addlunch();
            }
        });

        orderdinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddinner();
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

    public void openorderpage1(){
        Intent intent = new Intent(this, orderpage1.class);
        startActivity(intent);
    }


    public  void addbreakfast(){

        CheckBox thepla,bataka,achar,tea;
        String sthepla="0",sbataka="0",sachar="0",stea="0";
        thepla=findViewById(R.id.bfastthepla);
        bataka=findViewById(R.id.bfastpuva);
        achar=findViewById(R.id.bfastsalad);
        tea=findViewById(R.id.bfastpapad);
        if(thepla.isChecked()){

            sthepla="1";
            Log.e("TAG", "sthepla "+sthepla );
        }
        if(bataka.isChecked()){
            sbataka="1";
            Log.e("TAG", "sbataka "+sbataka );

        }
        if(achar.isChecked()){
            sachar="1";
            Log.e("TAG", "sachar "+sachar );

        }
        if(tea.isChecked()){
            stea="1";
            Log.e("TAG", "stea "+stea );

        }

        Intent intent = new Intent(this, orderpage1.class);
        intent.putExtra("thepla",sthepla);
        intent.putExtra("bataka",sbataka);
        intent.putExtra("achar",sachar);
        intent.putExtra("tea",stea);
        intent.putExtra("lunch","0");
        intent.putExtra("dinner","0");
        intent.putExtra("breakfast","1");
        startActivity(intent);






    }

    public  void  addlunch(){
        CheckBox roti,sabji,rise,dal,salad,papad;
        String sroti="0",ssabji="0",srise="0",sdal="0",ssalad="0",spapad="0";
        roti=findViewById(R.id.lunchroti);
        sabji=findViewById(R.id.lunchsabji);
        rise=findViewById(R.id.lunchrice);
        dal=findViewById(R.id.lunchDal);
        salad=findViewById(R.id.lunchsalad);
        papad=findViewById(R.id.lunchpapad);

        if(roti.isChecked()){
            sroti="1";
        }
        if(sabji.isChecked()){
            ssabji="1";
        }
        if(rise.isChecked()){
            srise="1";
        }
        if(dal.isChecked()){
            sdal="1";
        }
        if(salad.isChecked()){
            ssalad="1";
        }
        if(papad.isChecked()){
            spapad="1";
        }

        Intent intent = new Intent(this, orderpage1.class);
        intent.putExtra("roti",sroti);
        intent.putExtra("sabji",ssabji);
        intent.putExtra("rise",srise);
        intent.putExtra("dal",sdal);
        intent.putExtra("salad",ssalad);
        intent.putExtra("papad",spapad);
        intent.putExtra("lunch","1");
        intent.putExtra("dinner","0");
        intent.putExtra("breakfast","0");
        startActivity(intent);




    }
    public  void  adddinner(){
        CheckBox roti,sabji,moong,buttermilk,salad,papad;
        String sroti="0",ssabji="0",smoong="0",sbuttermilk="0",ssalad="0",spapad="0";
        roti=findViewById(R.id.dinnerroti);
        sabji=findViewById(R.id.dinnersabji);
        moong=findViewById(R.id.dinnerrice);
        buttermilk=findViewById(R.id.dinnerDal);
        salad=findViewById(R.id.dinnersalad);
        papad=findViewById(R.id.dinnerpapad);

        if(roti.isChecked()){
            sroti="1";
        }
        if(sabji.isChecked()){
            ssabji="1";
        }
        if(moong.isChecked()){
            smoong="1";
        }
        if(buttermilk.isChecked()){
            sbuttermilk="1";
        }
        if(salad.isChecked()){
            ssalad="1";
        }
        if(papad.isChecked()){
            spapad="1";
        }


        Intent intent = new Intent(this, orderpage1.class);
        intent.putExtra("droti",sroti);
        intent.putExtra("dsabji",ssabji);
        intent.putExtra("dmoong",smoong);
        intent.putExtra("dbuttermilk",sbuttermilk);
        intent.putExtra("dsalad",ssalad);
        intent.putExtra("dpapad",spapad);
        intent.putExtra("lunch","0");
        intent.putExtra("dinner","1");
        intent.putExtra("breakfast","0");
        startActivity(intent);




    }

    public void showorder(View view) {
        Intent intent = new Intent(this, showorder.class);
        startActivity(intent);
    }
}