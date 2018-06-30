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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

        private static final String TAG = "User Registration";
        private FirebaseAuth Auth;
        private String email;
        private String password;
        private EditText editTextEmail;
        private EditText editTextPassword;
        private Button signupbtn;
        private TextView title;
        private LinearLayout background;
        protected ProgressDialog progressDialog;
        private String password1;
        private String password2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        title = (TextView) findViewById(R.id.title);
        editTextEmail = (EditText) findViewById(R.id.userID);
        background = (LinearLayout) findViewById(R.id.login_background);
        editTextPassword = (EditText) findViewById(R.id.password);
        signupbtn = (Button) findViewById(R.id.loginBtn);

        background.setOnClickListener(this);
        editTextPassword.setOnKeyListener((View.OnKeyListener) this);
        title.setOnClickListener(this);
        signupbtn.setOnClickListener(this);

        Auth = FirebaseAuth.getInstance();


    }

    public void createUser(String email, String password){
        Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterUser.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }

        });
        FirebaseUser user = Auth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "User signed in. this function is in the createUser", LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "User couldn't be signed in. hum bhi ", LENGTH_LONG).show();
        }

    }

    private int sendEmailVerification() {
        final FirebaseUser user = Auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegisterUser.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        return 1;
        // [END send_email_verification]
    }

    @Override
    public void onClick(View v) {
        if(v == background){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        if(v==title){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

        if(v==signupbtn){
                String email1 = editTextEmail.getText().toString().trim();
                String password1 = editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(this, "Please enter Email", LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(this, "Please enter Password", LENGTH_LONG).show();
                }
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                createUser(email1, password1);
                Auth.signInWithEmailAndPassword(email1, password1);
                FirebaseUser user = Auth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(this, "User signed in", LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "User couldn't be signed in", LENGTH_LONG).show();
                }

                finish();
                startActivity(new Intent(getApplicationContext(), MainActivitySignIn.class));

        }

    }
    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction()== KeyEvent.ACTION_DOWN){
            createUser(email, password);
            //progressDialog.setMessage("Creating new User... ");
            //progressDialog.show();

            finish();
            startActivity(new Intent(getApplicationContext(), MainActivitySignIn.class));
        }
        return false;
    }
}
