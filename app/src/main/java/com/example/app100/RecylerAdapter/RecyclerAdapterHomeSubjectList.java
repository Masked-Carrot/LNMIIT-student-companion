package com.example.app100.RecylerAdapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app100.MainActivity;
import com.example.app100.R;
import com.example.app100.Subject;
import com.example.app100.card_click;
import com.example.app100.ui.home.*;
import com.example.app100.ui.home.HomeFragment;


import java.util.ArrayList;


public class RecyclerAdapterHomeSubjectList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Subject> subject;
    private Context context;


    public RecyclerAdapterHomeSubjectList(ArrayList<Subject> a, Context context){
        this.subject = a;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View view = inflater.inflate(R.layout.card_home_subject, parent, false);
        return new ViewHolder(view) {
        };

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ImageView imageView = holder.itemView.findViewById(R.id.home_card_subject_imageview);
        switch (position % 3) {
            case 2:
                imageView.setBackgroundResource(R.drawable.back1);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.back2);
                break;
            case 0:
                imageView.setBackgroundResource(R.drawable.back3);
                break;

        }
        Subject data = subject.get(position);
        String name = data.subject_name;
        TextView subject_name = holder.itemView.findViewById(R.id.home_card_subject_name);
        subject_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context , card_click.class);
            intent.putExtra("credits",subject.get(getAdapterPosition()).credits);
            intent.putExtra("getadapter" , getAdapterPosition());
            context.startActivity(intent);
        }
    }

}
