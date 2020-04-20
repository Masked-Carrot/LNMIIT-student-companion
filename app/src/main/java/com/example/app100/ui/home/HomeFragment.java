package com.example.app100.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app100.AddSubject;
import com.example.app100.DatabaseHelper;
import com.example.app100.R;
import com.example.app100.RecylerAdapter.RecyclerAdapterHomeSubjectList;
import com.example.app100.Subject;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private  ArrayList<Subject> subject = new ArrayList<>();
    private  DatabaseHelper databaseHelper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());
        populateArrayList();



//     homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
                /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ProgressBar progressBar = root.findViewById(R.id.home_fragment_main_progress_bar);
        TextView home_attendace_text = root.findViewById(R.id.home_fragment_main_attendance_percentage);


        int p = (int)calculate_percentage();
        progressBar.setProgress(p);
        home_attendace_text.setText(String.valueOf(p));







        RecyclerView recyclerView = root.findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
        recyclerView.setAdapter(new RecyclerAdapterHomeSubjectList(subject ,getContext()));


        ImageButton imageButton = root.findViewById(R.id.home_fragment_add);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , AddSubject.class);
                startActivity(intent);
            }
        });



        return root;
    }


    private void populateArrayList(){
        Log.d(TAG , "populating array list of home fragment");
        Cursor data = databaseHelper.getdata();
        while(data.moveToNext()){
            Subject sub = new Subject(data.getString(1) , data.getInt(2) ,data.getInt(3) , data.getInt(4));
            subject.add(sub);
        }
    }


    private double calculate_percentage(){
        int total = 0;
        int attended = 0;
        for(int i = 0 ; i < subject.size(); i++){
            total += subject.get(i).class_attended + subject.get(i).class_missed;
            attended += subject.get(i).class_attended;
        }
        if(total == 0)
            return 100;
        return (attended/(total*1.0)*100.0);
    }

}
