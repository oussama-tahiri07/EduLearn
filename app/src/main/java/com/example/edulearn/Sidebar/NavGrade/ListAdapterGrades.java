package com.example.edulearn.Sidebar.NavGrade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.edulearn.R;

public class ListAdapterGrades extends ArrayAdapter<UserGrades>{

    public ListAdapterGrades(Context context, ArrayList<UserGrades> userArrayList){
        super(context, 0, userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserGrades user = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_grades,parent,false);
        }

        TextView subject = convertView.findViewById(R.id.subject);
        TextView grade = convertView.findViewById(R.id.grade);

        subject.setText(user.subject);
        grade.setText(user.grade);

        return convertView;
    }
}
