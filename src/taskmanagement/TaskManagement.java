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
import java.util.logging.Logger;

/**
 * This class contains method to add task, a method to delete task , a method to
 * update task , a method to get data of task a method to check id is exist in
 * task or not, a method to display task and display bar
 *
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Nguyen Van Hieu
 */
public class TaskManagement {

    private static final GetInput getInput = new GetInput();
    private final static Logger LOGGER = Logger.getLogger("Task management");  //use logger to display to console

    /**
     * This method is used to display program options.
     */
    public void displayMenu() {
        System.out.println("=============== Task Management Program ===============");
        System.out.println("1.Add Task");
        System.out.println("2.Update Task");
        System.out.println("3.Delete Task");
        System.out.println("4.Display Task");
        System.out.println("5.Exit");
    }

    /**
     * This method is used to display the Id and name of the task.
     */
    public void taskTypeMenu() {
        System.out.print("Task type(must be from 1 to 4)");
        System.out.print("\n 1: Code \n 2: Test \n 3: Design \n 4: Review\n");
    }

    /**
     * This method is used to display the Id and name of the task.
     */
    public void titleBar() {
        System.out.printf("%-10s%-23s%-15s%-20s%-20s%-25s%-25s\n",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Review");
    }

    /**
     * This method is used to add task to list
     *
     * @param taskList list of task
     */
    public void addTask(ArrayList<Task> taskList) {
        System.out.println("\n========== Add Task ==========");
        int id = taskList.size() + 1;
        while (true) {
            //Get input requirement name
            String taskName = getInput.getInputString("Enter Task name: ", 20);
            taskTypeMenu();
            //Get input task type
            int taskType = getInput.getInputNumberInRange("Enter Task type: ", 1, 4);
            LocalDate date = getInput.getInputDate("Enter Date (MMM-dd-yyyy): ", 11);
            //Get input plan from
            float planFrom = Float.valueOf(getInput.getInputPlan("Enter From: ", false));
            float planTo = 0;
            while (true) {
                //Get input plan to
                planTo = Float.valueOf(getInput.getInputPlan("Enter To: ", false));
                if (planFrom < planTo) {
                    break;
                } else {
                    LOGGER.warning("The value of (To) needs to be greater than the value of (From)");
                }
            }
            String assignee = getInput.getInputString("Enter Asignee: ", 20);
            String reviewer = getInput.getInputString("Enter Reviewer: ", 20);
            taskList.add(new Task(id, taskName, taskType, date, planFrom, planTo, assignee, reviewer));// add new task to task list
            id++;
            if (!getInput.getInputAnswer("Do you want to add continue(Y/N)?")) {//ask user want to continue or not
                System.out.println("\nAdd sucessful!");
                break;
            }
        }
    }

    /**
     * This method is used to perform update task with id entered by user and
     * check entered id exist or not
     *
     * @param taskList list of task
     */
    public void updateTask(ArrayList<Task> taskList) {
        System.out.println("\n========== Update Task ==========");
        if (taskList.isEmpty()) {
            LOGGER.warning("The task list is empty. Please try again!");
            return;
        }
        while (true) {
            //Get the task Id to update.
            int id = getInput.getInputInteger("Enter ID: ");
            //Check if id exists in the list
            int index = checkExistId(id, taskList);
            if (index != -1) {
                Task taskInfor = taskList.get(index); // Task in index
                System.out.printf("|%-5s|%-25s|%-12s|%-20s|%-6s|%-6s|%-6s|%-20s|%-20s\n",
                        "ID", "Name", "Task Type", "Date", "From", "To", "Time", "Assign", "Reviewer");
                //Display task in index
                taskInfor.displayTaskWithPlan();
                //Get input update name
                String name = getInput.getInputString("Enter Task name: ", 20);
                if (!name.equalsIgnoreCase("nope")) {
                    taskInfor.setTaskName(name);
                }
                updateTaskType(taskInfor);
                updateDate(taskInfor);
                //Get input update plan from
                float planFrom = Float.parseFloat(getInput.getInputPlan("Enter From: ", true));
                //Enter end time
                float planTo = Float.parseFloat(getInput.getInputPlan("Enter To:  ", true));
                while (true) {
                    if ((planFrom == 0) && (planTo != 0) && (planTo <= taskInfor.getPlanFrom())) {
                        LOGGER.warning("The value of (To) needs to be greater than the value of (From) is " + taskInfor.getPlanFrom());
                        planTo = Float.parseFloat(getInput.getInputPlan("Enter To: ", true));
                    } else if ((planFrom != 0) && (planTo == 0) && (planFrom >= taskInfor.getPlanTo())) {
                        LOGGER.warning("The value of (From) must be less than the value of (To) is " + taskInfor.getPlanTo());
                        planFrom = Float.parseFloat(getInput.getInputPlan("Enter From: ", true));
                    } else if ((planFrom != 0) && (planTo != 0) && (planFrom > planTo)) {
                        LOGGER.warning("The value of (From) must be less than the value of (To).");
                        planFrom = Float.parseFloat(getInput.getInputPlan("Enter From: ", true));
                        planTo = Float.parseFloat(getInput.getInputPlan("Enter To: ", true));
                    } else {
                        if (planFrom != 0) {
                            taskInfor.setPlanFrom(planFrom);
                        }
                        if (planTo != 0) {
                            taskInfor.setPlanTo(planTo);
                        }
                        break;
                    }
                }
                //Get input update assignee
                String assignee = getInput.getInputString("Enter Asignee: ", 20);
                //Case of assignee is not "nope"
                if (!assignee.equalsIgnoreCase("nope")) {
                    //Update assignee
                    taskInfor.setAssignee(assignee);
                }
                //Get input update reviewer
                String reviewer = getInput.getInputString("Enter Reviewer: ", 20);
                if (!reviewer.equalsIgnoreCase("nope")) {
                    taskInfor.setReviewer(reviewer);
                }
                System.out.println("\nUpdate sucessful!");
                //Ask user to update continue
                if (!getInput.getInputAnswer("Do you want to update continue(Y/N)?")) {
                    break;
                }
            } else {
                if (!getInput.getInputAnswer("Code does not exist.Do you want to add this id(Y/N)?")) {
                    break;
                }
            }
        }
    }

    /**
     * This method is used to perform delete task with id entered by user and
     * check id exist or not
     *
     * @param taskList list of task
     */
    public void deleteTask(ArrayList<Task> taskList) {
        System.out.println("\n========== Delete Task ==========");
        if (taskList.isEmpty()) {
            LOGGER.warning("The task list is empty. Please try again!");
            return;
        }
        while (true) {
            //Get input id that user want to delete
            int id = getInput.getInputInteger("Enter id: ");
            int index = checkExistId(id, taskList);
            if (index != -1) {
                titleBar();//display bar took
                taskList.get(index).displayTask();
                //Delete task at index
                taskList.remove(index);
                System.out.print("\nDeleted sucessful!");
                if (!getInput.getInputAnswer("Do you want to delete continue(Y/N)?")) {//ask user want to delete continue
                    break;
                }
            } else {//id that user found not exist in list. Ask user want to delete another id
                if (!getInput.getInputAnswer("Id does not exist. Do you want to delete another id(Y/N)?")) {
                    break;
                }

            }
        }
    }

    /**
     * This method is used to get data of the task of descending according to
     * the ID
     *
     * @param taskList list contains all tasks
     */
    public void getDataTask(ArrayList<Task> taskList) {
        System.out.println("\n========== Task Descending Order ==========");
        if (taskList.isEmpty()) {//check list is empty or not
            System.out.println("Task is empty.Choose another option! ");
            return;
        }
        titleBar();//display bar tool
        for (int i = taskList.size() - 1; i >= 0; i--) {
            taskList.get(i).displayTask();
        }
        System.out.println("");
    }

    /**
     * This method update user input for Date when it is a valid date in format
     * "month-date-year". If user input nope remain information.
     *
     * @param task - task user want to update
     * @return a positive integer
     */
    private void updateDate(Task task) {
        while (true) {
            String inputDate = getInput.getInputString("Date: ", 11);
            if (inputDate.equalsIgnoreCase("nope")) {
                return;
            }
            LocalDate date = getInput.checkValidDate(inputDate);
            if (date != null) {
                task.setDate(date);
                return;
            }
        }
    }

    /**
     * This method is used to perform update task type of task.
     *
     * @param task
     */
    private void updateTaskType(Task task) {
        while (true) {
            try {
                taskTypeMenu();
                //Get input string by user
                String inputString = getInput.getInputString("Task type: ", 4);
                if (inputString.equalsIgnoreCase("nope")) {
                    return;
                }
                if (Integer.parseInt(inputString) >= 1 && Integer.parseInt(inputString) <= 4) {
                    task.setTaskTypeID(Integer.parseInt(inputString));
                    return;
                }
            } catch (Exception e) {
                System.out.println("Your input is not valid!Please enter again!");
            }

        }
    }

    /**
     * This method is used to check id is exist in list or not
     *
     * @param id id of task
     * @param taskList list of task
     * @return index of task if id exist in list and return -1 if task not exist
     * in list
     */
    public int checkExistId(int id, ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (id == taskList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

}
