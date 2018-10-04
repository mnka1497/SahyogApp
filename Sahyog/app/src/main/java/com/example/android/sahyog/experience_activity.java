package com.example.android.sahyog;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class experience_activity extends AppCompatActivity {
    TextView descText, plus, minus;
    private Menu menu;
    boolean flag = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       LayoutInflater inflater = getLayoutInflater();
                                       View alertLayout = inflater.inflate(R.layout.add_experience, null);
                                       options(alertLayout);
                                       /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();*/
                                   }
                               });

        descText = (TextView) findViewById(R.id.description_text);
        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.minus);




        plus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                plus.setVisibility(View.GONE);
                minus.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });

        minus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                minus.setVisibility(View.GONE);
                plus.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });

        final CircleImageView closeButton = findViewById(R.id.closeButton);
        closeButton.setEnabled(false);
        final Button delButton = findViewById(R.id.delete);
        delButton.setEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_experience, menu);
        this.menu=menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.Edit:
                if(flag){
                    menu.getItem(0).setIcon(R.drawable.ic_edit);
                    final CircleImageView closeButton = findViewById(R.id.closeButton);
                    closeButton.setVisibility(View.INVISIBLE);
                    closeButton.setEnabled(false);
                    final Button delButton = findViewById(R.id.delete);
                    delButton.setVisibility(View.INVISIBLE);
                    delButton.setEnabled(false);
                    flag = false;
                }
                else
                {
                    menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.check));
                    Toast.makeText(this, "Edit selected", Toast.LENGTH_SHORT)
                            .show();
                    final CircleImageView closeButton = findViewById(R.id.closeButton);
                    closeButton.setVisibility(View.VISIBLE);
                    closeButton.setEnabled(true);
                    final Button delButton = findViewById(R.id.delete);
                    delButton.setVisibility(View.VISIBLE);
                    delButton.setEnabled(true);
                    flag = true;
                }

        }

        return super.onOptionsItemSelected(item);
    }

    public void open_dialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertDialogBuilder.setMessage("Do you really want to delete");

        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(experience_activity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(experience_activity.this, "You clicked no button", Toast.LENGTH_LONG).show();
            }
        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundResource(R.drawable.circle);

        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setBackgroundResource(R.drawable.dialog_button);
        nbutton.setTextColor(Color.WHITE);
    }

    public void options(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


}