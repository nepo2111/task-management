/*
 * Copyright(C) 2005, Nguyen Van Hieu.
 * J1.S.P0071
 * Task management
 * 
 * Record of change:
 * DATE             Version             AUTHOR              DESCRIPTION
 * 2021-09-08       1.0                 Hieunv              First Implement
 */
package taskmanagement;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the main class. Contains a main method that performs task management
 * tasks.
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Nguyen Van Hieu
 */
public class Main {

    private static final GetInput VALIDATOR = new GetInput();

    /**
     * This is main method.This method is used to be perform task management
     * program
     *
     * @param args
     */
    public static void main(String[] args) {

        TaskManagement taskManagement = new TaskManagement();
        ArrayList<Task> taskList = new ArrayList<>();
        Task task = new Task();
        task.addTaskType();
        taskList.add(new Task(1, "Test 1", 1, LocalDate.of(2021, 12, 12), 12f, 15f, "A", "F"));
        taskList.add(new Task(2, "Test 2", 4, LocalDate.of(2021, 11, 20), 10f, 11.5f, "B", "E"));
        taskList.add(new Task(3, "Test 3", 3, LocalDate.of(2021, 12, 22), 8f, 9.5f, "C", "F"));
        while (true) {
            taskManagement.displayMenu();//display options to perform task management program
            int choice = VALIDATOR.getInputNumberInRange("Enter your choice (1 -> 5): ", 1, 5);//get input choice by user
            switch (choice) {
                case 1:
                    taskManagement.addTask(taskList);//add task by calling addTask()method
                    break;
                case 2:
                    taskManagement.updateTask(taskList);//update task by calling updateTask() method
                    break;
                case 3:
                    taskManagement.deleteTask(taskList);//delete task by calling deleteTask() method
                    break;
                case 4:
                    taskManagement.getDataTask(taskList);//get data task by calling getDataTask() method
                    break;
                case 5:
                    return;
            }
        }

    }
}
