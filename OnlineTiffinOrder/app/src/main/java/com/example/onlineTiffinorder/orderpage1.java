package com.example.onlineTiffinorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class orderpage1 extends AppCompatActivity{
    RadioButton rdbtnwithoutcorona;
    RadioButton rdbtnwithcorona;
    Button upload;
    Button btnnext;
    Spinner spinner;
    ImageButton btnProfile;
    Intent intent;
    EditText name,mobile,stdate,enddate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage1);
        intent=getIntent();
        rdbtnwithoutcorona = findViewById(R.id.rdbtnwithoutcorona);
        rdbtnwithcorona = findViewById(R.id.rdbtnwithcorona);
        btnnext = findViewById(R.id.btnnext);
        name = findViewById(R.id.inputname);
        mobile = findViewById(R.id.inputmobile_order);
        stdate = findViewById(R.id.fromdate);
        enddate = findViewById(R.id.todate);
        spinner = findViewById(R.id.spinnerqty);
        upload=findViewById(R.id.btnuploadfile);
        rdbtnwithoutcorona.setSelected(true);
        upload.setVisibility(View.GONE);
        btnProfile = findViewById(R.id.btnProfile);

        rdbtnwithoutcorona.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdbtnwithoutcorona.isChecked()){
                    upload.setVisibility(View.GONE);
                }else {
                    upload.setVisibility(View.VISIBLE);
                }
            }
        });

        rdbtnwithcorona.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdbtnwithcorona.isChecked()){
                    upload.setVisibility(View.VISIBLE);
                }else {
                    upload.setVisibility(View.GONE);
                }
            }
        });


            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.style_spinner,
                    getResources().getStringArray(R.array.qty)
            );

            adapter.setDropDownViewResource(R.layout.style_spinner_dropdown);
            spinner.setAdapter(adapter);

            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { openorderpage2(); }
            });
            btnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { openuserprofile(); }
            });

    }

    public void openuserprofile(){
        Intent intent = new Intent(this, userprofile.class);
        startActivity(intent);
    }

    public void openorderpage2(){
        String chkqty=spinner.getSelectedItem().toString();
        if(!(name.getText().length() <=0) && !(mobile.getText().length() <=0) &&
                !(stdate.getText().length() <=0) &&
                !(enddate.getText().length() <=0) && !chkqty.equals("Select Quantity")){
            String sname,smobile,sstdate,senddate,Qty,corona = "0";
            sname=name.getText().toString();
            smobile=mobile.getText().toString();
            sstdate=stdate.getText().toString();
            senddate=enddate.getText().toString();
            Qty=spinner.getSelectedItem().toString();
            if(rdbtnwithcorona.isSelected()){
                corona="1";
            }
            if(rdbtnwithoutcorona.isSelected()){
                corona="0";
            }


            String lunch=intent.getStringExtra("lunch");
            String breakfast=intent.getStringExtra("breakfast");
            String dinner=intent.getStringExtra("dinner");
            Log.e("TAG", "1lunch "+lunch );
            Log.e("TAG", "1breakfast "+breakfast );
            Log.e("TAG", "1lunch "+dinner );


            if(lunch.equals("1")){
                String sroti,ssabji,srise,sdal,ssalad,spapad;
                sroti=  intent.getStringExtra("roti");
                ssabji= intent.getStringExtra("sabji");
                srise=  intent.getStringExtra("rise");
                sdal=  intent.getStringExtra("dal");
                ssalad=  intent.getStringExtra("salad");
                spapad=  intent.getStringExtra("papad");


                Intent intent2 = new Intent(this, orderpage2.class);
                intent2.putExtra("roti",sroti);
                intent2.putExtra("sabji",ssabji);
                intent2.putExtra("rise",srise);
                intent2.putExtra("dal",sdal);
                intent2.putExtra("salad",ssalad);
                intent2.putExtra("papad",spapad);
                intent2.putExtra("lunch","1");
                intent2.putExtra("dinner","0");
                intent2.putExtra("breakfast","0");
                intent2.putExtra("name",sname);
                intent2.putExtra("mobile",smobile);
                intent2.putExtra("stdate",sstdate);
                intent2.putExtra("enddate",senddate);
                intent2.putExtra("qty",Qty);
                intent2.putExtra("corona",corona);

                startActivity(intent2);
            }
            else if(breakfast.equals("1")){
                String sthepla,sbataka,sachar,stea;
                sthepla= intent.getStringExtra("thepla");
                sbataka= intent.getStringExtra("bataka");
                sachar=  intent.getStringExtra("achar");
                stea=  intent.getStringExtra("tea");


                Intent intent2 = new Intent(this, orderpage2.class);
                intent2.putExtra("thepla",sthepla);
                intent2.putExtra("bataka",sbataka);
                intent2.putExtra("achar",sachar);
                intent2.putExtra("tea",stea);
                intent2.putExtra("lunch","0");
                intent2.putExtra("dinner","0");
                intent2.putExtra("breakfast","1");
                intent2.putExtra("name",sname);
                intent2.putExtra("mobile",smobile);
                intent2.putExtra("stdate",sstdate);
                intent2.putExtra("enddate",senddate);
                intent2.putExtra("qty",Qty);
                intent2.putExtra("corona",corona);




                startActivity(intent2);

            }
            else if(dinner.equals("1")){
                String sroti,ssabji,smoong,sbuttermilk,ssalad,spapad;
                sroti= intent.getStringExtra("droti");
                ssabji= intent.getStringExtra("dsabji");
                smoong= intent.getStringExtra("dmoong");
                sbuttermilk= intent.getStringExtra("dbuttermilk");
                ssalad= intent.getStringExtra("dsalad");
                spapad= intent.getStringExtra("dpapad");


                Intent intent2 = new Intent(this, orderpage2.class);
                intent2.putExtra("droti",sroti);
                intent2.putExtra("dsabji",ssabji);
                intent2.putExtra("dmoong",smoong);
                intent2.putExtra("dbuttermilk",sbuttermilk);
                intent2.putExtra("dsalad",ssalad);
                intent2.putExtra("dpapad",spapad);
                intent2.putExtra("lunch","0");
                intent2.putExtra("dinner","1");
                intent2.putExtra("breakfast","0");
                intent2.putExtra("name",sname);
                intent2.putExtra("mobile",smobile);
                intent2.putExtra("stdate",sstdate);
                intent2.putExtra("enddate",senddate);
                intent2.putExtra("qty",Qty);
                intent2.putExtra("corona",corona);



                startActivity(intent2);

            }
        }
        else {
            Toast.makeText(this, "select all fileld", Toast.LENGTH_SHORT).show();
        }



    }
}