package com.example.lab08;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;

    public StudentRecord() {
        this.studentID = "";
        this.midterm = 0.0f;
        this.assignments = 0.0f;
        this.finalExam = 0.0f;
    }

    public StudentRecord(String studentID, float assignments, float midterm, float finalExam) {
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }
}
