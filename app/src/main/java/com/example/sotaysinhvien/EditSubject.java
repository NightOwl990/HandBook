package com.example.sotaysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.sotaysinhvien.database.DBStudent;
import com.example.sotaysinhvien.model.Student;
import com.example.sotaysinhvien.model.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditSubject extends AppCompatActivity {

    private Toolbar toolbarEditSubject;
    public EditText edtTenSubject, edtSoTinChi, edtPlaceSubject;
    public Spinner spinnerTime;
    public Button btnThemEditSubject, btnSuaEditSubject, btnXoaEditSubject;
    boolean isedit;
    int idsubject;
    Subject subject;
    DBStudent dbEditSubject;
    private ArrayAdapter adapterSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        Anhxa();
        spinnerthoigian();

        // Khi click vào item listview, ta được đưa đến màn hình edit, lúc này nút thêm bị ẩn đi
        btnThemEditSubject.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Edit Subject");

        // Nhận dữ liệu
        Intent intent = getIntent();
        isedit = intent.getBooleanExtra("isedit", false);
        if (isedit){
            idsubject = intent.getIntExtra("idsubject", 0);
            subject = dbEditSubject.getSubjectId(idsubject);

            btnXoaEditSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbEditSubject.deleteSubjectByID(idsubject);
                    finish();
                }
            });

            btnSuaEditSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    laydulieu();
                    dbEditSubject.updateSubject(subject);
                    finish();
                }
            });
        } else {
            getSupportActionBar().setTitle("Add Subject");
            // Xóa trắng các ô nhập dữ liệu, đồng thời ẩn hai nút sửa, xóa
            subject = new Subject(0, "",0, "", "");
            btnSuaEditSubject.setVisibility(View.GONE);
            btnXoaEditSubject.setVisibility(View.GONE);
            btnThemEditSubject.setVisibility(View.VISIBLE);

            btnThemEditSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    laydulieu();
                    dbEditSubject.insertSubject(subject);
                    finish();
                }
            });
        }

        // Set dữ liệu cho các vùng nhập liệu
        edtTenSubject.setText(subject.getTensbj());
        edtSoTinChi.setText(String.valueOf(subject.getTinchisbj()));
        switch (subject.timesbj){
            case "1 tháng":
                spinnerTime.setSelection(0);
                break;
            case "2 tháng":
                spinnerTime.setSelection(1);
                break;
            case "3 tháng":
                spinnerTime.setSelection(2);
                break;
            case "4 tháng":
                spinnerTime.setSelection(3);
                break;
            case "5 tháng":
                spinnerTime.setSelection(4);
                break;
            case "6 tháng":
                spinnerTime.setSelection(5);
                break;
            case "1 năm":
                spinnerTime.setSelection(6);
                break;
            case "2 năm":
                spinnerTime.setSelection(7);
                break;
        }
        edtPlaceSubject.setText(subject.getPlacesbj());


    }

    private void Anhxa() {

        toolbarEditSubject = findViewById(R.id.toolbar_edit_subject);
        setSupportActionBar(toolbarEditSubject);
        toolbarEditSubject.setNavigationIcon(R.drawable.ic_back);
        toolbarEditSubject.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtTenSubject = findViewById(R.id.edt_ten_subject);
        edtSoTinChi = findViewById(R.id.edt_so_tin_chi);
        edtPlaceSubject = findViewById(R.id.edt_place_subject);
        spinnerTime = findViewById(R.id.spinner_time);
        btnThemEditSubject = findViewById(R.id.btn_them_edit_subject);
        btnSuaEditSubject = findViewById(R.id.btn_sua_edit_subject);
        btnXoaEditSubject = findViewById(R.id.btn_xoa_edit_subject);

        dbEditSubject = new DBStudent(this);
    }

    private void laydulieu() {
        // Lấy dữ liệu đã nhập của người dùng
        subject.tensbj = edtTenSubject.getText().toString().trim();
        subject.tinchisbj = Integer.parseInt(edtSoTinChi.getText().toString());
        subject.timesbj = spinnerTime.getSelectedItem().toString();
        subject.placesbj = edtPlaceSubject.getText().toString().trim();
    }

    private void spinnerthoigian(){
        ArrayList<String> arrayListTime = new ArrayList<String>();
        arrayListTime.add("1 tháng");
        arrayListTime.add("2 tháng");
        arrayListTime.add("3 tháng");
        arrayListTime.add("4 tháng");
        arrayListTime.add("5 tháng");
        arrayListTime.add("6 tháng");
        arrayListTime.add("1 năm");
        arrayListTime.add("2 năm");
        adapterSpinner = new ArrayAdapter(EditSubject.this, R.layout.support_simple_spinner_dropdown_item, arrayListTime);
        spinnerTime.setAdapter(adapterSpinner);
    }
}