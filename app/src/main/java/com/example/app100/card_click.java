package com.example.app100;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class card_click extends AppCompatActivity {

    TextView attendace_info;
    ProgressBar progressBar;
    TextView credits;
    TextView progress_percent;
    int bad;
    int good;
    Button add;
    Button minus;
    ImageButton performance;
    ImageButton subject_settings;
    String name;
    DatabaseHelper databaseHelper;
    ArrayList<Subject> subject = new ArrayList<>();
    LineChart chart;
    Typeface tflight;
    DatabaseHelper2 databaseHelper2;
    TextView card_name;



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(card_click.this  , MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_click);
        databaseHelper = new DatabaseHelper(this);
        Log.d(TAG , "card_click activity has been initiated");


        populateArrayList();
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        card_name = findViewById(R.id.card_click);
        performance = findViewById(R.id.card_click_performance_add);
        progress_percent = findViewById(R.id.card_click_progress_bar_percent);
        attendace_info = findViewById(R.id.card_click_attendance_text);
        progressBar = findViewById(R.id.card_click_progress_bar);
        credits = findViewById(R.id.card_click_credits_show);
        add = findViewById(R.id.card_click_add_button);
        minus = findViewById(R.id.card_click_minus_button);
        subject_settings = findViewById(R.id.card_click_attendance_settings);

        final Intent intent = getIntent();
        credits.setText(String.valueOf(intent.getIntExtra("credits", 0)));
        int adapter = intent.getIntExtra("getadapter" , 0);
        good  = subject.get(adapter).class_attended;
        bad = subject.get(adapter).class_missed;
        name = subject.get(adapter).subject_name;
        databaseHelper2 = new DatabaseHelper2(this , name);
        display();
        card_name.setText(name);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                good++;
                databaseHelper.append_data("CLASS_ATTENDED", name , good);
                display();

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bad++;
                databaseHelper.append_data("CLASS_MISSED", name, bad);
                display();

            }
        });

        performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(card_click.this, add_performace.class);
                intent1.putExtra("name" , name);
                startActivity(intent1);
            }
        });

        subject_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(card_click.this  , subject_settings.class);
                intent1.putExtra("name" , name);
                startActivity(intent1);
            }
        });




        chart = findViewById(R.id.linechart);
        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundColor(Color.rgb(255,255,255));

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        tflight = Typeface.DEFAULT;
        y.setTypeface(tflight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.BLACK);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.BLACK);

        chart.getAxisRight().setEnabled(false);



        chart.getLegend().setEnabled(false);

        chart.animateXY(1000, 1000);

        // don't forget to refresh the drawing
        chart.invalidate();
        ArrayList<Integer> m;
        ArrayList<Integer> t ;

        t = populatetotalArrayList();
        m = populatemarksArrayList();
        setData( m , t);


    }


    private void setData( ArrayList<Integer> m , ArrayList<Integer> t) {

        ArrayList<Entry> values = new ArrayList<>();
        System.out.println("adding chart data");

        for (int i = 0; i < m.size(); i++) {
            float p = (float)((m.get(i)*100.0)/t.get(i));
            System.out.println(m.get(i));
            values.add(new Entry(i, p));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.BLACK);
            set1.setHighLightColor(Color.rgb(51, 181, 229));
            set1.setColor(Color.rgb(51 , 181 , 229));
            set1.setFillColor(Color.rgb(51 , 181,229));
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            LineData data = new LineData(set1);
            data.setValueTypeface(Typeface.DEFAULT);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            chart.setData(data);
        }
    }






            void display(){
        int per = 100;
        if(good+bad != 0)
            per = (int)(good*100.0)/(good+bad);
        String str = "You have atttended "+ (good) +
                " out of " + (good + bad) +
                " classes";
        attendace_info.setText(str);
        progressBar.setProgress(per);
        progress_percent.setText(String.valueOf(per));
    }


    public void populateArrayList(){
        Log.d(TAG , "populating array list of home fragment");
        Cursor data = databaseHelper.getdata();
        while(data.moveToNext()){
            Subject sub = new Subject(data.getString(1) , data.getInt(2) ,data.getInt(3) , data.getInt(4));
            subject.add(sub);

        }
    }


    private ArrayList<Integer> populatemarksArrayList(){
        ArrayList<Integer> s = new ArrayList<>();
        Log.d(TAG , "populating array list marks ");
        Cursor data = databaseHelper2.getmarksdata(name);
        while(data.moveToNext()){
            s.add(data.getInt(1));
        }
        return s;
    }


    private ArrayList<Integer> populatetotalArrayList(){
        ArrayList<Integer> s = new ArrayList<>();
        Log.d(TAG , "populating array list total");
        Cursor data = databaseHelper2.gettotaldata(name);
        while(data.moveToNext()){
            s.add(data.getInt(2));
        }
        return s;
    }


}
