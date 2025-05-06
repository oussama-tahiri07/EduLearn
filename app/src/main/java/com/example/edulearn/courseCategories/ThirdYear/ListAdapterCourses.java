package com.example.edulearn.courseCategories.ThirdYear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edulearn.R;

public class ListAdapterCourses extends BaseAdapter {
    Context context;
    String[] listCourses;
    int[] pictureCourses;
    LayoutInflater layoutInflater;

    public ListAdapterCourses(Context context, String [] coursesList, int [] pictures){
        this.context = context;
        this.listCourses = coursesList;
        this.pictureCourses = pictures;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCourses.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_course_third_year, null);
        TextView textView = (TextView) convertView.findViewById(R.id.courses);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.picture_subject);
        textView.setText(listCourses[position]);
        imageView.setImageResource(pictureCourses[position]);
        return convertView;
    }
}
