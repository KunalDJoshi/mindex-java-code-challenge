package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public ReportingStructure() {

    }

    public ReportingStructure(Employee e) {
        this.employee = e;
    }

    public ReportingStructure(Employee e, int numberOfReports) {
        this.employee = e;
        this.numberOfReports = numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}