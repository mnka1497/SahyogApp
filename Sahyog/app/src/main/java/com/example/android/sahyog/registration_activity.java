package com.example.android.sahyog;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class registration_activity extends AppCompatActivity {
    private Button reg_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView tx = (TextView)findViewById(R.id.title);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Monotype Corsiva.ttf");

        tx.setTypeface(custom_font);

        reg_button = (Button) findViewById(R.id.regBtn);

        reg_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

				/*
				 * Intent is just like glue which helps to navigate one activity
				 * to another.
				 */
                Intent intent = new Intent(registration_activity.this,
                        login_activity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }
}
