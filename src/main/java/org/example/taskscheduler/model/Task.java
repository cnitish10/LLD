package org.example.taskscheduler.model;

import org.example.taskscheduler.enums.Priority;
import org.example.taskscheduler.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private static int idCounter = 0; // static counter shared by all tasks

    private final int taskId;
    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(String description, LocalDate dueDate, Priority priority, Status status) {
        this.taskId = ++idCounter;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
}
