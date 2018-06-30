package com.iitd.sahyog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iitd.sahyog.MainActivityFeed;
import com.iitd.sahyog.R;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivitySignIn extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private Button signInButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewForgot;
    protected ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView title;
    private LinearLayout background;
    private TextView newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        title = (TextView) findViewById(R.id.title);
        newUser = (TextView) findViewById(R.id.newUser);
        background = (LinearLayout) findViewById(R.id.login_background);
        signInButton = (Button) findViewById(R.id.loginBtn);
        editTextEmail = (EditText) findViewById(R.id.userID);
        editTextPassword = (EditText) findViewById(R.id.password);
        textViewForgot = (TextView) findViewById(R.id.forgotBtn);
        progressDialog = new ProgressDialog(this);
        textViewForgot.setOnClickListener(this);
        signInButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            if(user.isEmailVerified()){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivityFeed.class));
            }else{
                promter("Please verify email");
                return;
            }
        }

        background.setOnClickListener(this);
        newUser.setOnClickListener(this);
        editTextPassword.setOnKeyListener((View.OnKeyListener) this);
        title.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            signinUser();
        }
        if (v == textViewForgot) {
            ForotPassword();
        }
        if(v == background){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

   }
        if(v==title){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

        if(v==newUser){
            finish();
            createNew();
        }
    }

    public void createNew(){
        Intent i = new Intent(MainActivitySignIn.this, RegisterUser.class);
        startActivity(i);
    }
    public void signinUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email", LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password", LENGTH_LONG).show();
        }
        //All valid. Show Progress Dialog Sign in Success

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        progressDialog.setMessage("Signing you in... ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            boolean st = user.isEmailVerified();
                            if (st) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivityFeed.class));
                            } else
                                promter("Please verify email");
                                return;
                        } else {
                            promter("Invalid username or password");
                            return;
                        }


                    }
                });
    }
    public void ForotPassword(){

        return;
    }

    public  void promter(String s){
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }




    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction()== KeyEvent.ACTION_DOWN){
            signinUser();
        }
        return false;
    }
}
