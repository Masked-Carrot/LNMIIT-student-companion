package com.example.app100;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_performace extends AppCompatActivity {


    private static final String TAG ="add performace" ;
    DatabaseHelper2 databaseHelper2 ;
    EditText marks;
    EditText total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_performace);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        marks = findViewById(R.id.class_set_marks_your);
        total = findViewById(R.id.class_set_marks_total);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        databaseHelper2 = new DatabaseHelper2(this , name);

        Button add_performance = findViewById(R.id.performance_set);
        add_performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = Integer.parseInt(marks.getText().toString());
                int t = Integer.parseInt(total.getText().toString());
                boolean a = databaseHelper2.addData(t , m);
                if (!a) {
                    toastmessage();
                    Log.d(TAG , "marks data could not be added");
                }
                else {
                    Log.d(TAG , "marks data added");
                    Intent intent = new Intent(add_performace.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void toastmessage() {
        Toast.makeText(this, "Houston we have a problem", Toast.LENGTH_SHORT).show();
    }
}
