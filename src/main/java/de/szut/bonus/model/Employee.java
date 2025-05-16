package de.szut.bonus.model;

public class Employee {
    private   String name;
    private  int yearsAtCompany;
    private  int performanceRating;
    private  int completedProjects;
    private  int absenceDays;
    private  boolean isTeamLeader;

    public Employee(String name, int yearsAtCompany, int performanceRating,
                    int completedProjects, int absenceDays, boolean isTeamLeader) {
        this.name = name;
        this.yearsAtCompany = yearsAtCompany;
        this.performanceRating = performanceRating;
        this.completedProjects = completedProjects;
        this.absenceDays = absenceDays;
        this.isTeamLeader = isTeamLeader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsAtCompany() {
        return yearsAtCompany;
    }

    public void setYearsAtCompany(int yearsAtCompany) {
        this.yearsAtCompany = yearsAtCompany;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    public int getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(int completedProjects) {
        this.completedProjects = completedProjects;
    }

    public int getAbsenceDays() {
        return absenceDays;
    }

    public void setAbsenceDays(int absenceDays) {
        this.absenceDays = absenceDays;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }
}