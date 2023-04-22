package com.web.roomiez.user;

import jakarta.persistence.*;
import org.json.JSONObject;


@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private int ID;

    private int groupID;

    private String name;
    private String username; //email
    private String password;

    private Boolean locked = false;
    private Boolean enabled = false;

    //Constructor
    public User() {
        this.ID = 0;
        this.groupID = 0;
        this.name = "";
        this.password = "";
        this.username = "";
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", groupID=" + groupID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject body = new JSONObject();
        body.put("ID", ID);
        body.put("groupID", groupID);
        body.put("name", name);
        body.put("username", username);
        body.put("password", password);
        body.put("locked", locked);
        body.put("enabled", enabled);

        return body;
    }

//getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGroupID()
    {
        return this.groupID;
    }

    public void setGroupID(int groupID)
    {
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
