package org.yoon.planner;

public class Plan {
    private int id;
    private int level;
    private String title;
    private String detail;

    Plan(){}
    Plan(int id, int level, String title, String detail) {
        this.id = id;
        this.level = level;
        this.title = title;
        this.detail = detail;
    }



}
