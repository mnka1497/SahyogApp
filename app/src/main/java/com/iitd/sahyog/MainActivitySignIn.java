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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivitySignIn extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private Button signInButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewForgot;
    protected ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);

        title = (TextView) findViewById(R.id.textView);
        signInButton = (Button) findViewById(R.id.button2);
        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText4);
        textViewForgot = (TextView) findViewById(R.id.textView2);
        progressDialog = new ProgressDialog(this);

        textViewForgot.setOnClickListener(this);
        signInButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivityFeed.class));
        }
        //RelativeLayout background = (RelativeLayout) findViewById(R.background);
        //background.setOnClickListener(this);

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
        //if(v.getId()== R.id.background){
        //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

   // }
        if(v==title){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    public void signinUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "Please enter Email", LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter Password", LENGTH_LONG).show();
        }
        //All valid. Show Progress Dialog Sign in Success

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        progressDialog.setMessage("Signing you in... ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivityFeed.class));
                        }
                        else
                            promter();
                    }
                });

    }
    public void ForotPassword(){

        return;
    }

    public  void promter(){
        Toast toast = Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction()== KeyEvent.ACTION_DOWN){
            signinUser();
        }
        return false;
    }
}
