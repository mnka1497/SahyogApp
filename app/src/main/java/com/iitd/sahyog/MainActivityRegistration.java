package com.iitd.sahyog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivityRegistration extends AppCompatActivity {

    private String password1;
    private String password2;
    private String password;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String TAG = "User Registration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Validate password1 and set to password
        password= getPassword();

        //check if both passwords match
        if(!password.equals(password2)){
            Toast.makeText(this, "Passwords do not match", LENGTH_SHORT).show();
            //password2.clear();
        }else{
            user.updatePassword(password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User password updated.");
                                Toast.makeText(MainActivityRegistration.this, "Your password has been updated", LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivityFeed.class));
                            }
                        }
                    });
        }




    }
    //Validates password1
    public String getPassword() {
        if(password1.length()>=7){
            if(password1.matches(".*[a-z].*")){
                if(password1.matches(".*[0-9]")){
                    password = password1;
                }
                else{
                    Toast.makeText(this, "Password must contain at least one digit [0-9]", LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Password must contain at least one alphabet [0-9]", LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Password must contain at least 8 characters", LENGTH_SHORT).show();
        }
        return password;
    }
}
