package com.example.app100.ui.schedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app100.DatabaseHelper;
import com.example.app100.MainActivity;
import com.example.app100.R;
import com.example.app100.Subject;

import java.util.ArrayList;

import ca.antonious.materialdaypicker.MaterialDayPicker;
import ca.antonious.materialdaypicker.SingleSelectionMode;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ScheduleFragment extends Fragment {




    private ScheduleViewModel scheduleViewModel;
    private ArrayList<Subject> subject = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    ImageButton bus ;
    ImageButton mess;
    ImageButton clas;
    ImageButton year;
    String doc;
    WebView wv;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());



        scheduleViewModel =  ViewModelProviders.of(this).get(ScheduleViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        clas = root.findViewById(R.id.class_schedule);
        bus = root.findViewById(R.id.bus_schedule);
        mess = root.findViewById(R.id.mess_schedule);
        wv = root.findViewById(R.id.web);
        year = root.findViewById(R.id.year_schedule);

        clas.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                doc="<iframe src='https://docdro.id/1nuf4Lj' width='100%' height='100%'  style='border: none;'></iframe>";


                wv.setVisibility(WebView.VISIBLE);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setAllowFileAccess(true);
                wv.loadData(doc, "text/html", "UTF-8");

            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                doc="<iframe src='https://docdro.id/La9lZlu' width='100%' height='100%'  style='border: none;'></iframe>";
                wv.setVisibility(WebView.VISIBLE);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setAllowFileAccess(true);
                wv.loadData(doc, "text/html", "UTF-8");
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                doc="<iframe src='https://docdro.id/9rtGIHT' width='100%' height='100%'  style='border: none;'></iframe>";
                wv.setVisibility(WebView.VISIBLE);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setAllowFileAccess(true);
                wv.loadData(doc, "text/html", "UTF-8");
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                doc="<iframe src='https://docdro.id/EvfzeCI' width='100%' height='100%'  style='border: none;'></iframe>";
                wv.setVisibility(WebView.VISIBLE);
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setAllowFileAccess(true);
                wv.loadData(doc, "text/html", "UTF-8");
            }
        });








        return root;
    }





}
