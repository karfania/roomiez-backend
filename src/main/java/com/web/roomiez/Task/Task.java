package com.web.roomiez.Task;

import jakarta.persistence.*;

@Entity
@Table(name = "TaskTable")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)

    private int taskID;
    private int assigneeID;
    private String assigneeName;

    private int groupID;

    private String name;
    private String startDate; //Ask if this data type will be fine
    private String endDate;
    private String startTime; //TODO establish format of startDate and startTime
    private String endTime;
    private int progress;
    private String description;

    private String repeatTask;

    // Default Constructor
    public Task(){

    }

    public Task(int taskID){
        this.taskID = taskID;
    }

    // Constructor
    public Task(int taskID, int assigneeID, String assigneeName, int groupID, String name, String startDate, String endDate, String startTime, String endTime, int progress, String description,
    String repeatTask) {
        this.taskID = taskID;
        this.assigneeID = assigneeID;
        this.assigneeName = assigneeName;
        this.groupID = groupID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.progress = progress;
        this.description = description;
        this.repeatTask = repeatTask;
    }

    // Getters and setters for data members

    public int getID() {
        return taskID;
    }

    public void setID(int ID) {
        this.taskID = ID;
    }
    public int getAssigneeID() {
        return assigneeID;
    }

    public void setAssigneeID(int assigneeID) {
        this.assigneeID = assigneeID;
    }

    public String getAssigneeName() { return assigneeName; }

    public void setAssigneeName(String assigneeName){
        this.assigneeName = assigneeName;
    }
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepeat() {return repeatTask;}

    public void setRepeat(String repeat) {this.repeatTask = repeat;}
    // Methods for task management

    public void createTask() {
        // Implementation for adding a new task to the database
    }

    public void deleteTask() {
        // Implementation for deleting a task from the database
    }

    public void updateTask(int newProgress, int newAssigneeID) {
        // Implementation for updating a task's progress and/or assigned user
        setProgress(newProgress);
        setAssigneeID(newAssigneeID);
    }
}


