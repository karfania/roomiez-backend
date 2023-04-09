package com.web.roomiez.user;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    //private int groupID;
    private String username;
    private String password;


    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

   // private String username;
   // private String password;

    public User() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

//    public int getGroupID() {
//        return groupID;
//    }
//
//    public void setGroupID(int groupID) {
//        this.groupID = groupID;
//    }



}
