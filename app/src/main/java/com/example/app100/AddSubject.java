package com.example.app100;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;






public class AddSubject extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText name;
    EditText credits;
    Button save;
    private static final String TAG = "Add subject";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_add_subject);

        databaseHelper = new DatabaseHelper(this);
        name = findViewById(R.id.add_subject_name);
        credits = findViewById(R.id.add_subject_credits);
        save = findViewById(R.id.add_subject_btn_save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME;
                int CREDITS;
                NAME = name.getText().toString();
                CREDITS = Integer.parseInt(credits.getText().toString());
                if (name.length() == 0 || credits.length() == 0)
                    toastmessage();
                else
                    AddData(NAME, CREDITS);
            }
        });
    }


    private void AddData(String name, int credits)  {
        boolean insert = databaseHelper.addData(name, credits , 0  ,0);
        if (!insert) {
            toastmessage();
            Log.d(TAG , "data could not be added");
        }
        else {
            Log.d(TAG , "data added");
            Intent intent = new Intent(AddSubject.this, MainActivity.class);
            startActivity(intent);
        }

    }

    private void toastmessage() {
        Toast.makeText(this, "Houston we have a problem", Toast.LENGTH_SHORT).show();
    }

}
