package com.example.sotaysinhvien.model;

public class Subject {
    public int idsbj;
    public String tensbj;
    public int tinchisbj;
    public String timesbj;
    public String placesbj;

    public Subject(int idsbj, String tensbj, int tinchisbj, String timesbj, String placesbj) {
        this.idsbj = idsbj;
        this.tensbj = tensbj;
        this.tinchisbj = tinchisbj;
        this.timesbj = timesbj;
        this.placesbj = placesbj;
    }

    public int getIdsbj() {
        return idsbj;
    }

    public void setIdsbj(int idsbj) {
        this.idsbj = idsbj;
    }

    public String getTensbj() {
        return tensbj;
    }

    public void setTensbj(String tensbj) {
        this.tensbj = tensbj;
    }

    public int getTinchisbj() {
        return tinchisbj;
    }

    public void setTinchisbj(int tinchisbj) {
        this.tinchisbj = tinchisbj;
    }

    public String getTimesbj() {
        return timesbj;
    }

    public void setTimesbj(String timesbj) {
        this.timesbj = timesbj;
    }

    public String getPlacesbj() {
        return placesbj;
    }

    public void setPlacesbj(String placesbj) {
        this.placesbj = placesbj;
    }
}
