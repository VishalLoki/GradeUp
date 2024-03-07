package com.example.gradeup;

public class Sem_Helper_Class {
    String sname;
    String scredit;
    String sgrade;

    public Sem_Helper_Class(String sname, String scredit, String sgrade) {
        this.sname = sname;
        this.scredit = scredit;
        this.sgrade = sgrade;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getScredit() {
        return scredit;
    }

    public void setScredit(String scredit) {
        this.scredit = scredit;
    }

    public String getSgrade() {
        return sgrade;
    }

    public void setSgrade(String sgrade) {
        this.sgrade = sgrade;
    }
}
