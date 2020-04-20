package com.example.app100;

public class Subject {


    public String subject_name;
    public int credits;
    public int class_attended;
    public int class_missed;


    public int getClass_attended() {
        return class_attended;
    }

    public void setClass_attended(int class_attended) {
        this.class_attended = class_attended;
    }

    public int getClass_missed() {
        return class_missed;
    }

    public void setClass_missed(int class_missed) {
        this.class_missed = class_missed;
    }

    public Subject(String subject, int credit , int class_a , int class_m){
        this.subject_name = subject;
        this.credits = credit;
        this.class_attended = class_a;
        this.class_missed = class_m;

    }


    public String getSubject_name() {
        return subject_name;
    }
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

}
