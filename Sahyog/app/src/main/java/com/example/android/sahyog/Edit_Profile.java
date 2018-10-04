package com.example.android.sahyog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.theartofdev.edmodo.cropper.CropImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Profile extends AppCompatActivity {

    private Button button;
    private Button ImgBtn;
    private static final int gallery_pick = 1;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);

        //Spinner for Department


        Spinner DeptSpinner = (Spinner) findViewById(R.id.profile_Dept);

        String[] items = new String[]{"Department", "CSE", "IT", "ECE", "MAE"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };

        DeptSpinner.setAdapter(adapter);

        DeptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Spinner for Course


        Spinner CourseSpinner = (Spinner) findViewById(R.id.profile_Course);

        String[] courses = new String[]{"Course", "BTech", "MTech"};

        ArrayAdapter<String> course_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, courses) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };

        CourseSpinner.setAdapter(course_adapter);

        CourseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Spinner for Year


        Spinner YearSpinner = (Spinner) findViewById(R.id.profile_Year);

        String[] year = new String[]{"Year", "I", "II", "III", "IV"};

        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, year) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };

        YearSpinner.setAdapter(year_adapter);

        YearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        button = (Button) findViewById(R.id.doneBtn);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

				/*
				 * Intent is just like glue which helps to navigate one activity
				 * to another.
				 */
                Intent intent = new Intent(Edit_Profile.this,
                        experience_activity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });


        ImgBtn = findViewById(R.id.add_photo);
        ImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "SELECT IMAGE"), gallery_pick);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == gallery_pick && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();
                CropImage.activity(imageUri)
                        .setAspectRatio(1, 1)
                        .start(this);
            //Toast.makeText(SettingsActivity.this,imageUri,Toast.LENGTH_LONG).show();

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    ((CircleImageView) findViewById(R.id.img)).setImageURI(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }

    }


}
