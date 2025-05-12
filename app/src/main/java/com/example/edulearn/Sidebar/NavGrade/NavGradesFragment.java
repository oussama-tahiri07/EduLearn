package com.example.edulearn.Sidebar.NavGrade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import com.example.edulearn.R;

public class NavGradesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_grade, container, false);

        ListView listView = view.findViewById(R.id.list_view);

        ArrayList<UserGrades> subjectGradeList = new ArrayList<>();
        subjectGradeList.add(new UserGrades("[I12-S1] MAT1.001 CALCULUS I", "18"));
        subjectGradeList.add(new UserGrades("[I12-S1] MAT1.002 LINEAR ALGEBRA", "15"));
        subjectGradeList.add(new UserGrades("[I12-S1] BIO1.001 CELLULAR BIOLOGY", "17"));
        subjectGradeList.add(new UserGrades("[I12-S1] CHE1.001 GENERAL CHEMISTRY I", "20"));
        subjectGradeList.add(new UserGrades("[I12-S1] PHY1.001 FUNDAMENTAL PHYSIC I", "12"));
        subjectGradeList.add(new UserGrades("[I12-S1] ICT.001 MOBILE DEVELOPMENT", "17"));
        subjectGradeList.add(new UserGrades("[I12-S1] ICT.002 BASIC PROGRAMMING", "20"));
        subjectGradeList.add(new UserGrades("[I12-S1] MAT1.003 CALCULUS II", "16"));
        subjectGradeList.add(new UserGrades("[I12-S1] MAT1.004 DISCRETE MATH", "19"));
        subjectGradeList.add(new UserGrades("[I12-S1] ICT1.003 COMPUTER ARCHITECTURE", "16"));


        ListAdapterGrades adapter = new ListAdapterGrades(getActivity(), subjectGradeList);
        listView.setAdapter(adapter);
        return view;
    }
}