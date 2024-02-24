package com.example.gradeup;

public class Item {
    String semester;
    String credit;
    String gpa;

    public Item(String semester, String credit, String gpa) {
        this.semester = semester;
        this.credit = credit;
        this.gpa = gpa;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}
