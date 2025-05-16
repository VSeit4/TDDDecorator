package de.szut.bonus.model;

public class Employee {

    private  int performanceRating;


    public Employee( int performanceRating) {

        this.performanceRating = performanceRating;

    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }
}