package com.web.roomiez.Task;

public class Task{
    private int ID;
    private int assigneeID;
    private int groupID;
    private String name;
    private String startDate; //Ask if this data type will be fine
    private String endDate;
    private String startTime; //TODO establish format of startDate and startTime
    private String endTime;
    private Progress progress;
    private String description;

    // Constructor
    public Task(int ID, int assigneeID, int groupID, String name, String startDate, String endDate, String startTime, String endTime, Progress progress, String description) {
        this.ID = ID;
        this.assigneeID = assigneeID;
        this.groupID = groupID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.progress = progress;
        this.description = description;
    }

    // Getters and setters for data members

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getAssigneeID() {
        return assigneeID;
    }

    public void setAssigneeID(int assigneeID) {
        this.assigneeID = assigneeID;
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

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Methods for task management

    public void createTask() {
        // Implementation for adding a new task to the database
    }

    public void deleteTask() {
        // Implementation for deleting a task from the database
    }

    public void updateTask(Progress newProgress, int newAssigneeID) {
        // Implementation for updating a task's progress and/or assigned user
        setProgress(newProgress);
        setAssigneeID(newAssigneeID);
    }
}


