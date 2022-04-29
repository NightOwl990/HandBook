package com.example.sotaysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sotaysinhvien.adapter.StudentAdapter;
import com.example.sotaysinhvien.adapter.SubjectAdapter;
import com.example.sotaysinhvien.database.DBStudent;
import com.example.sotaysinhvien.model.Student;
import com.example.sotaysinhvien.model.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    public static final int RESULT_SUBJECT_ACTIVITY = 999;
    private Button btnAddSubject;
    private Toolbar toolbarSubject;
    private ListView lvSubject;
    private SubjectAdapter subjectAdapter;
    private ArrayList<Subject> mListSubject;
    private DBStudent dbStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        init();
        event();
        loadAllSubject();

    }

    private void event() {
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectActivity.this, EditSubject.class);
                intent.putExtra("isedit", false);
                startActivityForResult(intent, RESULT_SUBJECT_ACTIVITY);
            }
        });

        lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subject subject = (Subject) subjectAdapter.getItem(i);
                Intent intent = new Intent(SubjectActivity.this, EditSubject.class);
                intent.putExtra("isedit", true);
                intent.putExtra("idsubject", subject.idsbj);
                startActivityForResult(intent, RESULT_SUBJECT_ACTIVITY);
            }
        });

    }

    private void loadAllSubject() {
        mListSubject.clear();
        mListSubject.addAll(dbStudent.getAllSubject());
    }

    private void init(){
        lvSubject = findViewById(R.id.lv_subject);
        btnAddSubject = findViewById(R.id.btn_add_subject);
        toolbarSubject = findViewById(R.id.toolbar_subject);

        // thiết lập toolbar
        setSupportActionBar(toolbarSubject);
        getSupportActionBar().setTitle("Subject");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSubject.setNavigationIcon(R.drawable.ic_back);
        toolbarSubject.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbStudent = new DBStudent(this);
        mListSubject = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(mListSubject);
        lvSubject.setAdapter(subjectAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SUBJECT_ACTIVITY) {
            loadAllSubject();
            subjectAdapter.notifyDataSetChanged();
        }
    }
}