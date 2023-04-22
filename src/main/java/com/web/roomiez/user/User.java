package com.web.roomiez.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.roomiez.Task.Task;
import com.web.roomiez.group.Group;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private int ID;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="groupID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

//    @OneToMany(targetEntity = com.web.roomiez.Task.Task.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Task> userTasks;

    //private int groupID;
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
                ", group=" + group +
//                ", userTasks=" + userTasks +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }

//getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Group getGroup()
    {
        return this.group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public int getGroupID() {
        return group.getGroupID();
    }

    public void setGroupID(int groupID) {
        this.group.setGroupID(groupID);
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
//    public List<Task> getTasks() {
//        return userTasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.userTasks = tasks;
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
