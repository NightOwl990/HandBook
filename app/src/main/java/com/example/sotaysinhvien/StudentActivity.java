package com.example.sotaysinhvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sotaysinhvien.adapter.StudentAdapter;
import com.example.sotaysinhvien.database.DBStudent;
import com.example.sotaysinhvien.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    public static final int RESULT_ACTIVITY = 123;
    private Button btnAddStudent;
    private Toolbar toolbarStudent;
    private ListView lvStudent;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> mListStudent;
    private DBStudent dbStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        init();
        event();
        loadAllStudent();

    }

    private void event() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, EditStudent.class);
                intent.putExtra("isupdate", false);
                startActivityForResult(intent, RESULT_ACTIVITY);
            }
        });

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) studentAdapter.getItem(i);
                Intent intent = new Intent(StudentActivity.this, EditStudent.class);
                intent.putExtra("isupdate", true);
                intent.putExtra("idstudent", student.idsv);
                startActivityForResult(intent, RESULT_ACTIVITY);
            }
        });

    }

    private void loadAllStudent() {
        mListStudent.clear();
        mListStudent.addAll(dbStudent.getAllStudent());
    }

    private void init(){
        lvStudent = findViewById(R.id.lv_student);
        btnAddStudent = findViewById(R.id.btn_add_student);
        toolbarStudent = findViewById(R.id.toolbar_student);

        // thiết lập toolbar
        setSupportActionBar(toolbarStudent);
        getSupportActionBar().setTitle("Student");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarStudent.setNavigationIcon(R.drawable.ic_back);
        toolbarStudent.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dbStudent = new DBStudent(this);
        mListStudent = new ArrayList<>();
        studentAdapter = new StudentAdapter(mListStudent);
        lvStudent.setAdapter(studentAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_ACTIVITY) {
            loadAllStudent();
            studentAdapter.notifyDataSetChanged();
        }
    }
}