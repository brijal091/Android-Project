package com.example.onlineTiffinorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loginpage extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100 ;
    private TextView gotoRegistration;
    EditText mobno,pass;
    Button login;
    Spinner spinner;
    SharedPreferences role;
    SharedPreferences.Editor myEdit;
    GoogleSignInClient mGoogleSignInClient;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                Toast.makeText(this,"Hello Welcome " + personName , Toast.LENGTH_SHORT).show();
            }
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "You are logged in succesfuly", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message", e.toString());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        Button google_auth_btn = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

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