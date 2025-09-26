package org.example.taskscheduler;

import org.example.taskscheduler.enums.Priority;
import org.example.taskscheduler.model.Task;
import org.example.taskscheduler.service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

public class TaskSchedulerApp {
    public static void main(String args[]){
        TaskService taskService = new TaskService();

        // create task
        taskService.createTasks("Prep for DSA", LocalDate.of(2025, 8, 30), Priority.HIGH);
        taskService.createTasks("Prep for LLD", LocalDate.of(2025, 10, 1), Priority.MEDIUM);
        taskService.createTasks("Prep for SpringBoot", LocalDate.of(2025, 10, 15), Priority.LOW);

        //delete task
        taskService.deleteTask(3);

        //list all task
        List< Task > allTask = taskService.getAllTasks();
        for(Task t: allTask){
            System.out.println(t.getDescription());
        }

        //complete task
        taskService.completeTask(2);

        //sort by priority
        List< Task > sortedTask1 = taskService.sortTasksByPriority();
        for(Task t: sortedTask1){
            System.out.println(t.getDescription());
        }

        //sort by due date
        List< Task > sortedTask2 = taskService.sortByDueDate();
        for(Task t: sortedTask2){
            System.out.println(t.getDescription());
        }

        //sort by due date
        List< Task > sortedTask3 = taskService.overDueTask();
        for(Task t: sortedTask3){
            System.out.println("over due task " +t.getDescription() + " Due date was : " + t.getDueDate());
        }
    }
}
