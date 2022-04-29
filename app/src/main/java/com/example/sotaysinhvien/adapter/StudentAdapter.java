package com.example.sotaysinhvien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sotaysinhvien.EditStudent;
import com.example.sotaysinhvien.R;
import com.example.sotaysinhvien.database.DBStudent;
import com.example.sotaysinhvien.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter{

    private final List<Student> mListStudent;

    public StudentAdapter(List<Student> mListStudent) {
        this.mListStudent = mListStudent;
    }

    @Override
    public int getCount() {
        return mListStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return mListStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mListStudent.get(i).idsv;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewSinhvien;
        if (view == null){
            viewSinhvien = View.inflate(viewGroup.getContext(), R.layout.dong_student, null);
        } else viewSinhvien = view;

        Student student = (Student) getItem(i);

        ((TextView) viewSinhvien.findViewById(R.id.tv_ma_sinh_vien)).setText(String.format("%d", student.masv));
        ((TextView) viewSinhvien.findViewById(R.id.tv_ten_sinh_vien)).setText(String.format("%s", student.tensv));

        return viewSinhvien;
    }


}
