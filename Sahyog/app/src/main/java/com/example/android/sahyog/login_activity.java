package com.example.android.sahyog;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class login_activity extends AppCompatActivity {
    private Button logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tx = (TextView)findViewById(R.id.title);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Monotype Corsiva.ttf");

        tx.setTypeface(custom_font);

        logBtn = (Button) findViewById(R.id.loginBtn);

        logBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

				/*
				 * Intent is just like glue which helps to navigate one activity
				 * to another.
				 */
				Intent intent = new Intent(login_activity.this,
                        notifications_activity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

}
