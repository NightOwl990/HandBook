package com.example.sotaysinhvien.model;

public class Student {
    public int idsv;
    public int masv;
    public String tensv;
    public String gioitinh;
    public String ngaysinh;

    public Student(int idsv, int masv, String tensv, String gioitinh, String ngaysinh) {
        this.idsv = idsv;
        this.masv = masv;
        this.tensv = tensv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public int getIdsv() {
        return idsv;
    }

    public void setIdsv(int idsv) {
        this.idsv = idsv;
    }

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
