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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The class contains attributes of Task , a default constructor, a
 * parameterized Constructor, getter and setter methods to get the value of each
 * parameter 
 * 
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Nguyen Van Hieu
 */
public class Task {

    private static final ArrayList<TaskType> TASKTYPE = new ArrayList<>();

    private int id;                    // declare ID of task
    private TaskType taskType;         // declare Task Type
    private String taskName;    // declare name of task
    private LocalDate date;            // declare Date
    private float planFrom;            // declare start time
    private float planTo;              // declare end time
    private String assignee;           // declare Task assignee
    private String reviewer;           // declare Reviewer

    /**
     * This is default constructor 
     */
    public Task() {
    }

    /**
     * This is the method of passing parameters to the constructor
     *
     * @param id - ID of task
     * @param taskTypeId - Task Type
     * @param requirementName - name of task
     * @param date - Date
     * @param planFrom - start time
     * @param planTo - end time
     * @param assignee - Task assignee
     * @param reviewer - Reviewer
     */
    public Task(int id, String requirementName, int taskTypeId, LocalDate date, float planFrom, float planTo, String assignee, String reviewer) {
        this.id = id;
        this.taskName = requirementName;
        this.taskType = getTaskTypeByID(taskTypeId);
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    /**
     * This method is used to add task type to list TaskType
     */
    public void addTaskType() {
        TASKTYPE.add(new TaskType(1, "Code"));
        TASKTYPE.add(new TaskType(2, "Test"));
        TASKTYPE.add(new TaskType(3, "Design"));
        TASKTYPE.add(new TaskType(4, "Review"));
    }

    /**
     * This method is used to get task object from list
     *
     * @param taskTypeId - entered id by user
     * @return TaskType object
     */
    private TaskType getTaskTypeByID(int taskTypeId) {
        for (TaskType taskType : TASKTYPE) {
            if (taskType.getTaskTypeID() == taskTypeId) {
                return taskType;
            }
        }
        return null;
    }

    /**
     * This method is used to get task ID
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * This method is used to set ID of task
     *
     * @param id ID of task
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method is used to get Task type
     *
     * @return a string is task type
     */
    public TaskType getTaskTypeId() {
        return taskType;
    }

    /**
     * This method is used to set task type
     *
     * @param taskType 
     */
    public void setTaskTypeID(int taskType) {
        this.taskType = getTaskTypeByID(taskType);
    }

    /**
     * This method is used to get name 
     *
     * @return a string is task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * This method is used to set task name
     *
     * @param requirementName 
     */
    public void setTaskName(String tasktName) {
        this.taskName = tasktName;
    }

    /**
     * This method is used to get date
     *
     * @return a string is date format
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This method is used to set date.
     *
     * @param date 
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * This method is used to get start time
     *
     * @return real number
     */
    public float getPlanFrom() {
        return planFrom;
    }

    /**
     * This method is used to set start time
     *
     * @param planFrom start time
     */
    public void setPlanFrom(float planFrom) {
        this.planFrom = planFrom;
    }

    /**
     * This method is used to get end time
     *
     * @return real number
     */
    public float getPlanTo() {
        return planTo;
    }

    /**
     * This method is used to set to time.
     *
     * @param planTo 
     */
    public void setPlanTo(float planTo) {
        this.planTo = planTo;
    }

    /**
     * This method is used to get assignee 
     *
     * @return a string is assignee
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * This method is used to set assignee
     *
     * @param assignee assignee 
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * This method is used to get reviewer 
     *
     * @return a string is reviewer
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * This method is used to set reviewer name
     *
     * @param reviewer reviewer name 
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    
    /**
     * This method is used to display information of task.
     */
    public void displayTask() {
        System.out.printf("%-10s%-23s%-15s%-20s%-20s%-25s%-25s\n", id, taskName, taskType.getTaskTypeName(), date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), (planTo - planFrom), assignee, reviewer);
    }
    
    /**
     * This method to display information of task assigned contain planFrom and
     * planTo
     */
    public void displayTaskWithPlan() {
        System.out.printf("|%-5s|%-25s|%-12s|%-20s|%-6s|%-6s|%-6s|%-20s|%-20s\n", id, taskName, taskType.getTaskTypeID(),
                date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), planFrom, planTo, planTo - planFrom, assignee, reviewer);
    }

}
