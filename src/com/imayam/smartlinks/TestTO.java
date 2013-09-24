package com.imayam.smartlinks;

import java.util.Date;

public class TestTO {

    private int id;
    private Date startTime;
    private Date endTime;
    private String option;
    private String reference;
    private int questionCount;
    private long duration;
    private String examDate;
    private Date examStart;
    private String timeLeft;
    public TestTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getDiff() {
        long diff = 0;
        if (endTime != null && startTime != null) {
            diff = (endTime.getTime() - startTime.getTime()) / 1000;
        }
        return diff;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public java.util.Date getExamStart() {
        return examStart;
    }

    public void setExamStart(java.util.Date examStart) {
        this.examStart = examStart;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
