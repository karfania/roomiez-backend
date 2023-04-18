package com.web.roomiez.user;

import com.web.roomiez.Task.Task;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.ArrayList;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int groupID;
    private String name;
    //Username must be an email
    private String username;
    private String password;
    //private ArrayList<Task> tasks;
    //private Boolean completedCurrentTasks;

    private Boolean locked = false;
    private Boolean enabled = false;

    //Constructor
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", groupID=" + groupID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    public ArrayList<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(ArrayList<Task> tasks) {
//        this.tasks = tasks;
//    }

//    public Boolean getCompletedCurrentTasks() {
//        return completedCurrentTasks;
//    }
//
//    public void setCompletedCurrentTasks(Boolean completedCurrentTasks) {
//        this.completedCurrentTasks = completedCurrentTasks;
//    }


    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }





}
