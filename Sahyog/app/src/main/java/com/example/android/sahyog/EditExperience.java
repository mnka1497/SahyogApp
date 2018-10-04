package com.example.android.sahyog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class EditExperience extends AppCompatActivity {

    private Button addskill;
    private Button addexp;
    int value=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addskill=findViewById(R.id.addskill);
        addexp=findViewById(R.id.addexp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_experience, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void skill_dialog(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditExperience.this);
        alertDialog.setTitle("Add SKILL");
        alertDialog.setMessage("Enter the skill");

        final EditText input = new EditText(EditExperience.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialogBuilder = alertDialog.create();
        alertDialogBuilder.show();


    }

    public void exp_dialog(final View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Select Option");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.spinner, null);
        Dialog.setView(dialogView);

        Spinner spinnercategory = (Spinner) dialogView
                .findViewById(R.id.viewSpin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnercategory.setAdapter(adapter);

        spinnercategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View arg1,
                                       int arg2, long arg3) {
                final String selItem = parent.getSelectedItem().toString();

                switch(selItem){
                    case "Internship":
                        value=1;
                        break;
                    case "Project":
                        value=2;
                        break;
                    case "Training":
                        value=3;
                        break;
                    case "Certificate":
                        value=4;
                        break;
                    case "Publication":
                        value=5;
                        break;
                    default:
                        value=0;
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        switch(value){
                            case 1:
                                internship_dialog(view);
                                break;
                            case 2:
                                project_dialog(view);
                                break;
                            case 3:
                                training_dialog(view);
                                break;
                            case 4:
                               certificate_dialog(view);
                               break;
                            case 5:
                                publication_dialog(view);
                                break;
                        }
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

    public void internship_dialog(View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Internship");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.activity_internship, null);
        Dialog.setView(dialogView);

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

    public void project_dialog(View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Project");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.activity_project, null);
        Dialog.setView(dialogView);

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

    public void training_dialog(View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Training");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.activity_training, null);
        Dialog.setView(dialogView);

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

    public void certificate_dialog(View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Certificate");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.activity_certificate, null);
        Dialog.setView(dialogView);

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

    public void publication_dialog(View view) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("Publication");

        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = li.inflate(R.layout.activity_publication, null);
        Dialog.setView(dialogView);

        Dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        Dialog.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        Dialog.show();

    }

}
