package org.example.taskscheduler.service;

import org.example.taskscheduler.enums.Priority;
import org.example.taskscheduler.enums.Status;
import org.example.taskscheduler.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    List<Task> allTasks;

    public TaskService() {
        this.allTasks = new ArrayList<>();
        this.taskHashMap = new HashMap<>();
    }

    HashMap<Integer, Task> taskHashMap;

    //CRUD
    public void createTasks(String description, LocalDate dueDate, Priority priority){
        Task task = new Task(description, dueDate, priority, Status.PENDING);
        allTasks.add(task);
        taskHashMap.put(task.getTaskId(), task);
        System.out.println("task created with id: " + task.getTaskId());
    }

    public void updateTask(int taskId, String description, LocalDate dueDate, Priority priority){
        Task task = taskHashMap.get(taskId);
        if(task== null) return;
        allTasks.remove(task);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setStatus(Status.PENDING);
        allTasks.add(task);
        taskHashMap.put(task.getTaskId(), task);
        System.out.println("task updated with id: " + task.getTaskId());
    }

    public boolean deleteTask(int taskId){
        Task task = taskHashMap.get(taskId);
        if(task==null){
            System.out.println("tsak doesnt exist");
            return false;
        }
        allTasks.remove(task);
        taskHashMap.remove(taskId);
        System.out.println("deleted task" + taskId);
        return true;
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }
    public boolean completeTask(int taskId){
        Task task = taskHashMap.get(taskId);
        if(task==null){
            System.out.println("tsak doesnt exist");
            return false;
        }
        allTasks.remove(task);
        task.setStatus(Status.DONE);
        allTasks.add(task);
        taskHashMap.put(taskId, task);
        System.out.println("Task Completed " + taskId);
        return true;
    }

    public List<Task> sortTasksByPriority(){
        List<Task> sortedTask = allTasks.stream().sorted(Comparator.comparingInt(a -> a.getPriority().getValue())).collect(Collectors.toList());
        return sortedTask;
    }

    public List<Task> sortByDueDate() {
        return allTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public List<Task> overDueTask() {
        return allTasks.stream()
                .filter(task -> task.getDueDate().isBefore(LocalDate.now())
                        || task.getDueDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }


}
