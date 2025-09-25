package org.example.meetingscheduler.model;

import org.example.meetingscheduler.enums.Department;

public class Employee {
    private int employeeId;
    private String employeeName;
    private Department department;

    public Employee(int employeeId, String employeeName, Department department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Department getDepartment() {
        return department;
    }
}
