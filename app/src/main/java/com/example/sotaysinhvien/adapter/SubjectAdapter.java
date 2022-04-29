package com.example.sotaysinhvien.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sotaysinhvien.R;
import com.example.sotaysinhvien.model.Student;
import com.example.sotaysinhvien.model.Subject;

import java.util.List;

public class SubjectAdapter extends BaseAdapter{

    private final List<Subject> mListSubject;

    public SubjectAdapter(List<Subject> mListSubject) {
        this.mListSubject = mListSubject;
    }

    @Override
    public int getCount() {
        return mListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return mListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mListSubject.get(i).idsbj;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View viewSubject;
        if (view == null){
            viewSubject = View.inflate(viewGroup.getContext(), R.layout.dong_subject, null);
        } else viewSubject = view;

        Subject subject = (Subject) getItem(i);

        ((TextView) viewSubject.findViewById(R.id.tv_ten_mon_hoc)).setText(String.format("%s", subject.tensbj));
        ((TextView) viewSubject.findViewById(R.id.tv_so_tin_chi)).setText(String.format("%d", subject.tinchisbj));

        return viewSubject;
    }


}
