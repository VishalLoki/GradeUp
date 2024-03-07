package com.example.gradeup;

public class Item1 {
    String sem;
    String gpa;

    public Item1(String sem, String gpa) {
        this.sem = sem;
        this.gpa = gpa;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}
