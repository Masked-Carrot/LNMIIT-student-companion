package com.example.app100;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class subject_settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_settings);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Button save = findViewById(R.id.settings_set);
        final EditText attend = findViewById(R.id.class_set_attend);
        final EditText missed = findViewById(R.id.class_set_missed);
        Intent intent  = getIntent();
        final String name = intent.getStringExtra("name");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attend.getText().toString().equals(""))
                    attend.setText("-1");
                if(missed.getText().toString().equals(""))
                    missed.setText("-1");
                final int a = Integer.parseInt(attend.getText().toString());
                final int m = Integer.parseInt(missed.getText().toString());
                if(a != -1)
                    databaseHelper.append_data("CLASS_ATTENDED", name , a);
                if(m != -1)
                    databaseHelper.append_data("CLASS_MISSED", name , m);
                Intent intent1 = new Intent(subject_settings.this , MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}
