package com.example.sotaysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.sotaysinhvien.database.DBStudent;
import com.example.sotaysinhvien.model.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditStudent extends AppCompatActivity {

    private Toolbar toolbarEditStudent;
    public EditText edtTenStudent, edtMaStudent, edtNgaySinhStudent;
    public RadioButton rdoNam, rdoNu;
    public Button btnThemEditStudent, btnSuaEditStudent, btnXoaEditStudent;
    boolean isupdate;
    int idstudent;
    Student student;
    DBStudent dbEditStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Anhxa();

        // Khi click vào item listview, ta được đưa đến màn hình edit, lúc này nút thêm bị ẩn đi
        btnThemEditStudent.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Edit Student");

        // Nhận dữ liệu
        Intent intent = getIntent();
        isupdate = intent.getBooleanExtra("isupdate", false);
        if (isupdate){
            idstudent = intent.getIntExtra("idstudent", 0);
            student = dbEditStudent.getStudentId(idstudent);

            btnXoaEditStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbEditStudent.deleteStudentByID(idstudent);
                    finish();
                }
            });

            btnSuaEditStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    laydulieu();
                    dbEditStudent.updateStudent(student);
                    finish();
                }
            });
        } else {
            getSupportActionBar().setTitle("Add Student");
            // Xóa trắng các ô nhập dữ liệu, đồng thời ẩn hai nút sửa, xóa
            student = new Student(0, 0,"", rdoNu.isChecked() + "", "");
            btnSuaEditStudent.setVisibility(View.GONE);
            btnXoaEditStudent.setVisibility(View.GONE);
            btnThemEditStudent.setVisibility(View.VISIBLE);

            btnThemEditStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    laydulieu();
                    dbEditStudent.insertStudent(student);
                    finish();
                }
            });
        }

        // Set dữ liệu cho các vùng nhập liệu
        edtTenStudent.setText(student.getTensv());
        edtMaStudent.setText(String.valueOf(student.getMasv()));
        if (student.getGioitinh().equals("Nam")){
            rdoNam.setChecked(true);
        } else rdoNu.setChecked(true);
        edtNgaySinhStudent.setText(String.valueOf(student.getNgaysinh()));

        edtNgaySinhStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chonngay();
            }
        });


    }

    private void Anhxa() {

        toolbarEditStudent = findViewById(R.id.toolbar_edit_student);
        setSupportActionBar(toolbarEditStudent);
        toolbarEditStudent.setNavigationIcon(R.drawable.ic_back);
        toolbarEditStudent.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtTenStudent = findViewById(R.id.edt_ten_student);
        edtMaStudent = findViewById(R.id.edt_ma_student);
        edtNgaySinhStudent = findViewById(R.id.edt_ngaysinh_student);
        rdoNam = findViewById(R.id.rdo_nam);
        rdoNu = findViewById(R.id.rdo_nu);
        btnThemEditStudent = findViewById(R.id.btn_them_edit_student);
        btnSuaEditStudent = findViewById(R.id.btn_sua_edit_student);
        btnXoaEditStudent = findViewById(R.id.btn_xoa_edit_student);

        dbEditStudent = new DBStudent(this);
    }

    private void Chonngay(){

        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // i:năm - i1:tháng - i2:ngày
                calendar.set(i, i1, i2);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgaySinhStudent.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void laydulieu() {
        // Lấy dữ liệu đã nhập của người dùng

        student.masv = Integer.parseInt(edtMaStudent.getText().toString());
        student.tensv = edtTenStudent.getText().toString().trim();
        if (rdoNam.isChecked()){
            student.gioitinh = "Nam";
        } else {
            student.gioitinh = "Nữ";
        }
        student.ngaysinh = edtNgaySinhStudent.getText().toString().trim();
    }
}