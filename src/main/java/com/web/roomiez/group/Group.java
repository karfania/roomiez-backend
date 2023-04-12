package com.web.roomiez.group;

//import com.web.roomiez.Task.Task;
//import com.web.roomiez.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "GroupTable")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int groupID;
    private String groupName;
//    @Transient
//    private ArrayList<User> usersInGroup;
//    private ArrayList<Task> groupTasks;


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
        return "Group{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                '}';
    }
//    public ArrayList<User> getUsersInGroup() {
//        return usersInGroup;
//    }
//
//    public void setUsersInGroup(ArrayList<User> usersInGroup) {
//        this.usersInGroup = usersInGroup;
//    }
//
//    public ArrayList<Task> getGroupTasks() {
//        return groupTasks;
//    }
//
//    public void setGroupTasks(ArrayList<Task> groupTasks) {
//        this.groupTasks = groupTasks;
//    }
}
