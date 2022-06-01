package com.example.onlineTiffinorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineTiffinorder.api.Api;
import com.example.onlineTiffinorder.api.ApiClient;
import com.example.onlineTiffinorder.api.CommanResponse;
import com.example.onlineTiffinorder.api.responce.User;
import com.example.onlineTiffinorder.storage.sareprefrencelogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderpage2 extends AppCompatActivity {
    Spinner spinner;
    ImageButton btnProfile;
    EditText address,pincode,req;
    Button addorderfinal;
    Intent intent;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage2);
        intent=getIntent();
        if(sareprefrencelogin.getInstance(this).islogin())
        {
            User user= sareprefrencelogin.getInstance(orderpage2.this).getuser();
            userid=user.getId()+"";

        }
        addorderfinal = findViewById(R.id.addorderfinal);
        address = findViewById(R.id.textadd);
        pincode = findViewById(R.id.textpincode);
        req = findViewById(R.id.textspecial);
        spinner = findViewById(R.id.spinnerbld);
        btnProfile = findViewById(R.id.btnProfile);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.style_spinner,
                getResources().getStringArray(R.array.Brunch_linner)
        );

        adapter.setDropDownViewResource(R.layout.style_spinner_dropdown);
        spinner.setAdapter(adapter);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openuserprofile(); }
        });

        addorderfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addorder();
            }
        });
    }

    public void addorder() {
        String saddress,spincode,sreq;
        saddress=address.getText()+"";
        spincode=pincode.getText()+"";
        sreq=req.getText()+"";
        if(!saddress.isEmpty() && !spincode.isEmpty() && !sreq.isEmpty() ){

            String lunch=intent.getStringExtra("lunch");
            String breakfast=intent.getStringExtra("breakfast");
            String dinner=intent.getStringExtra("dinner");
            Log.e("TAG", "lunch "+lunch );
            Log.e("TAG", "breakfast "+breakfast );
            Log.e("TAG", "lunch "+dinner );

            if(lunch.equals("1")){
                String sroti,ssabji,srise,sdal,ssalad,spapad,sname,smobile,sstdate,senddate,sqty,scorona;
                sroti=  intent.getStringExtra("roti");
                ssabji= intent.getStringExtra("sabji");
                srise=  intent.getStringExtra("rise");
                sdal=  intent.getStringExtra("dal");
                ssalad=  intent.getStringExtra("salad");
                spapad=  intent.getStringExtra("papad");
                sname= intent.getStringExtra("name");
                smobile=  intent.getStringExtra("mobile");
                sstdate=  intent.getStringExtra("stdate");
                senddate=  intent.getStringExtra("enddate");
                sqty=  intent.getStringExtra("qty");
                scorona= intent.getStringExtra("corona");

                Api api = ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call = api.addlunch("addbreakfast",userid,sroti,ssabji,srise,sdal,ssalad,spapad,
                        sname,smobile,sqty,scorona,sstdate,senddate,saddress,spincode,sreq);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(orderpage2.this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        else {
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(orderpage2.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });




            }
            else if(breakfast.equals("1")){
                String sthepla,sbataka,sachar,stea,sname,smobile,sstdate,senddate,sqty,scorona;
                sthepla= intent.getStringExtra("thepla");
                sbataka= intent.getStringExtra("bataka");
                sachar=  intent.getStringExtra("achar");
                stea=  intent.getStringExtra("tea");
                sname= intent.getStringExtra("name");
                smobile=  intent.getStringExtra("mobile");
                sstdate=  intent.getStringExtra("stdate");
                senddate=  intent.getStringExtra("enddate");
                sqty=  intent.getStringExtra("qty");
                scorona= intent.getStringExtra("corona");


                Api api = ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call = api.addbreakfast("addbreakfast",userid,sthepla,sbataka,sachar,stea,
                        sname,smobile,sqty,scorona,sstdate,senddate,saddress,spincode,sreq);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(orderpage2.this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        else {
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(orderpage2.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });



            }
            else if(dinner.equals("1")){
                String sroti,ssabji,smoong,sbuttermilk,ssalad,spapad,sname,smobile,sstdate,senddate,sqty,scorona;
                sroti= intent.getStringExtra("droti");
                ssabji= intent.getStringExtra("dsabji");
                smoong= intent.getStringExtra("dmoong");
                sbuttermilk= intent.getStringExtra("dbuttermilk");
                ssalad= intent.getStringExtra("dsalad");
                spapad= intent.getStringExtra("dpapad");
                sname= intent.getStringExtra("name");
                smobile=  intent.getStringExtra("mobile");
                sstdate=  intent.getStringExtra("stdate");
                senddate=  intent.getStringExtra("enddate");
                sqty=  intent.getStringExtra("qty");
                scorona= intent.getStringExtra("corona");
                Api api = ApiClient.getClient().create(Api.class);
                Call<CommanResponse> call = api.adddinner("adddinner",userid,sroti,ssabji,smoong,sbuttermilk,ssalad,spapad,
                        sname,smobile,sqty,scorona,sstdate,senddate,saddress,spincode,sreq);
                call.enqueue(new Callback<CommanResponse>() {
                    @Override
                    public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                        if(response.body().getSuccess()==200){
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(orderpage2.this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        else {
                            Toast.makeText(orderpage2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommanResponse> call, Throwable t) {
                        Toast.makeText(orderpage2.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });



            }

        }else {
            Toast.makeText(this, "fill all field", Toast.LENGTH_SHORT).show();
        }

    }

    public void openuserprofile(){
        Intent intent = new Intent(this, userprofile.class);
        startActivity(intent);
    }
}