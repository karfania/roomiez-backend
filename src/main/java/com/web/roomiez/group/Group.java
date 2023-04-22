package com.web.roomiez.group;

import jakarta.persistence.*;

@Entity
@Table(name = "GroupTable")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int groupID;
    private String groupName;

    // default constructor
    public Group() {
        this.groupID = 0;
        this.groupName = "";
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
}
