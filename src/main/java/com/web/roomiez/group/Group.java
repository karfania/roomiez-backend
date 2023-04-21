package com.web.roomiez.group;

import com.web.roomiez.Task.Task;
import com.web.roomiez.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "GroupTable")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int groupID;
    private String groupName;
    @Transient
    @OneToMany(targetEntity = com.web.roomiez.user.User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> usersInGroup;

    @Transient
    @OneToMany(targetEntity = com.web.roomiez.Task.Task.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> groupTasks;


    // default constructor
    public Group() {
    }

    // constructor assuming MySQL-generated ID
    public Group(String groupName) {
        this.groupName = groupName;
    }

    // constructor with pre-determined groupID
    public Group(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                '}';
    }
//    public List<User> getUsersInGroup() {
//        return usersInGroup;
//    }
//
//    public void setUsersInGroup(List<User> usersInGroup) {
//        this.usersInGroup = usersInGroup;
//    }

//    public List<Task> getGroupTasks() {
//        return groupTasks;
//    }
//
//    public void setGroupTasks(List<Task> groupTasks) {
//        this.groupTasks = groupTasks;
//    }
}
