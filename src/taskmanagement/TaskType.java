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

/**
 * This class contains attributes of task type object and methods is get and set
 * value for task type
 *
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Nguyen Van Hieu
 */
public class TaskType {

    private int taskTypeID; // declare id of task type
    private String taskTypeName; // declare name of task type

    /**
     * This is a default constructor with no parameters.
     */
    public TaskType() {
    }

    /**
     * This is the constructor to pass the parameter 
     *
     * @param taskTypeID
     * @param taskType
     */
    public TaskType(int taskTypeID, String taskTypeName) {
        this.taskTypeID = taskTypeID;
        this.taskTypeName = taskTypeName;
    }

    /**
     * This method is used to get task type
     *
     * @return a string is task type
     */
    public String getTaskTypeName() {
        return taskTypeName;
    }

    /**
     * This method is used to get id of task type
     *
     * @return integer number is id of task type
     */
    public int getTaskTypeID() {
        return taskTypeID;
    }

    /**
     * This method is used to set task type
     *
     * @param taskType
     */
    public void setTaskTypeName(String taskType) {
        this.taskTypeName = taskType;
    }

    /**
     * This method is used to set id of task type
     *
     * @param taskTypeID
     */
    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

}
