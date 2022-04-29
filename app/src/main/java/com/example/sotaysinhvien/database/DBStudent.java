package com.example.sotaysinhvien.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import com.example.sotaysinhvien.model.Student;
import com.example.sotaysinhvien.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class DBStudent extends SQLiteOpenHelper {

    private static final String DATABASE_TABLE = "sinhvien.db";
    public static final String TABLE_NAME = "student";
    public static final String TABLE_SUBJECT = "subject";
    public String SQLQuery1 = "INSERT INTO student VAlUES (null, 181402937 + '', 'Nguyễn Văn Chính', 'Nam', '09/09/2000')";
    public String SQLQuery2 = "INSERT INTO subject VAlUES (null, 'Cơ sở dữ liệu', 3 + '', '5 tháng', '401A2')";
    public DBStudent(@Nullable Context context) {
        super(context, DATABASE_TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create table student
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                "idsv INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "masv INTEGER, " +
                "tensv TEXT, " +
                "gioitinh TEXT, " +
                "ngaysinh TEXT" + ")";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(SQLQuery1);

        // Create table subject
        String query2 = "CREATE TABLE " + TABLE_SUBJECT + "(" +
                "idsbj INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tensbj TEXT, " +
                "tinchisbj INTEGER, " +
                "timesbj TEXT, " +
                "placesbj TEXT" + ")";
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(SQLQuery2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        onCreate(sqLiteDatabase);
    }

    public List<Student> getAllStudent(){
        List<Student> mlist = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int idstudent = cursor.getInt(0);
            int mastudent = cursor.getInt(1);
            String tenstudent = cursor.getString(2);
            String gioitinhstudent = cursor.getString(3);
            String ngaysinhstudent = cursor.getString(4);
            mlist.add(new Student(idstudent, mastudent, tenstudent, gioitinhstudent, ngaysinhstudent));
            cursor.moveToNext();
        }
        cursor.close();
        Log.e("AAA", "getAllStudent");
        return mlist;

    }

    public Student getStudentId(int ID){
        Student student = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE idsv = ?", new String[]{ID + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int studentID = cursor.getInt(0);
            int studentMa = cursor.getInt(1);
            String studentTen = cursor.getString(2);
            String studentGioitinh = cursor.getString(3);
            String studentNgaysinh = cursor.getString(4);
            student = new Student(studentID, studentMa, studentTen, studentGioitinh, studentNgaysinh);
        }
        cursor.close();
        return student;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE student SET masv=?, tensv=?, gioitinh=?, ngaysinh=? WHERE idsv = ?",
                new String[]{student.getMasv() + "", student.getTensv(), student.getGioitinh(), student.getNgaysinh() + "", student.idsv + ""});
        Log.e("AAA", "update");
    }

    public void insertStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO student (masv, tensv, gioitinh, ngaysinh) VALUES (?,?,?,?)",
                new String[]{student.getMasv() + "", student.getTensv(), student.getGioitinh(), student.getNgaysinh() + ""});
    }

    public void deleteStudentByID(int StudentID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM student where idsv = ?", new String[]{String.valueOf(StudentID)});
    }

    public List<Subject> getAllSubject(){
        List<Subject> mlistsbj = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_SUBJECT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int idsbj = cursor.getInt(0);
            String tensbj = cursor.getString(1);
            int tinchisbj = cursor.getInt(2);
            String timesbj = cursor.getString(3);
            String placesbj = cursor.getString(4);
            mlistsbj.add(new Subject(idsbj, tensbj, tinchisbj, timesbj, placesbj));
            cursor.moveToNext();
        }
        cursor.close();
        return mlistsbj;
    }

    public Subject getSubjectId(int ID){
        Subject subject = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SUBJECT + " WHERE idsbj = ?", new String[]{ID + ""});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int subjectID = cursor.getInt(0);
            String subjectTen = cursor.getString(1);
            int subjectTinchi = cursor.getInt(2);
            String subjectTime = cursor.getString(3);
            String subjectPlace = cursor.getString(4);
            subject = new Subject(subjectID, subjectTen, subjectTinchi, subjectTime, subjectPlace);
        }
        cursor.close();
        return subject;
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE subject SET tensbj=?, tinchisbj=?, timesbj=?, placesbj=? WHERE idsbj = ?",
                new String[]{subject.getTensbj(), subject.getTinchisbj() + "", subject.getTimesbj() + "", subject.getPlacesbj(), subject.getIdsbj() + ""});
    }

    public void insertSubject(Subject subject) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO subject (tensbj, tinchisbj, timesbj, placesbj) VALUES (?,?,?,?)",
                new String[]{subject.getTensbj(), subject.getTinchisbj() + "", subject.getTimesbj() + "", subject.getPlacesbj()});
    }

    public void deleteSubjectByID(int SubjectID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM subject where idsbj = ?", new String[]{String.valueOf(SubjectID)});
    }

}
