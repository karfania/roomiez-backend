package com.web.roomiez.Task;

import com.web.roomiez.group.Group;
import com.web.roomiez.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "TaskTable")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    private int taskID;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="assigneeID", referencedColumnName = "ID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="groupID", referencedColumnName = "groupID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
    
    private String assigneeName;
    private String name;
    private String startDate; //Format YYYY-MM-DD
    private String endDate;
    private String startTime; //Format HH:MM:SS
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

    public Task(String name, String repeatTask){
        this.name = name;
        this.repeatTask = repeatTask;
    }

    // Constructor
    public Task(int taskID, User user, Group group, String name, String startDate, String endDate, String startTime, String endTime, int progress, String description,
                String repeatTask) {
        this.taskID = taskID;
        this.user = user;
        this.group = group;
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

    public void setUser(User user) {
        this.user = user;
    }

    public int getID() {
        return taskID;
    }

    public void setID(int ID) {
        this.taskID = ID;
    }
    public int getAssigneeID() {
        return user.getID();
    }

    public void setAssignee(User assignee) {
        this.user = assignee;
    }

    public String getAssigneeName() { return assigneeName; }

    public void setAssigneeName(String assigneeName){
        this.assigneeName = assigneeName;
    }
    public int getGroupID() {
        return group.getGroupID();
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public String getRepeatTask() {return repeatTask;}

    public void setRepeatTask(String repeat) {this.repeatTask = repeat;}

    public void updateTask(int newProgress, User newAssignee) {
        // Implementation for updating a task's progress and/or assigned user
        setProgress(newProgress);
        setAssignee(newAssignee);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", user=" + user +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", progress=" + progress +
                ", description='" + description + '\'' +
                '}';
    }
}
