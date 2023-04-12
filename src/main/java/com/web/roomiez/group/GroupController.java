package com.web.roomiez.group;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.boot.configurationprocessor.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// API LAYER
@RestController
@RequestMapping(path="/groups")
public class GroupController {

    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService)
    {
        this.groupService = groupService;
    }

    // get all groups currently stored in web application
    @GetMapping("/all")
    public List<Group> getGroups()
    {
        return groupService.getGroups();
    }

    // search for group by ID, returning HTTP 404 NOT FOUND error if it does not exist in DB
    @GetMapping("/{groupID}")
    public ResponseEntity<String> getGroupByID(@PathVariable("groupID") int groupID) {
        try {
            Group group = groupService.getGroupByID(groupID);
            // body creation
            JSONObject body = new JSONObject();
            body.put("groupID", groupID);
            body.put("groupName", group.getGroupName());
            return new ResponseEntity<>(body.toString(), HttpStatus.FOUND);
        } catch (IllegalStateException ise) {
            // if the group is not found, report bad request
            return new ResponseEntity<>(ise.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (JSONException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // search for groups by groupName, returning HTTP404 NOT FOUND error if none exist in DB
    // have to use RequestParam so distinction between ID search can be made: /groups?groupName=...
    @GetMapping
    public ResponseEntity<String> getGroupsByName(@RequestParam("groupName") String groupName) {
        try {
            List<Group> groups = groupService.getGroupsByName(groupName);
            System.out.println(groups);
            JSONArray jsonObjects = new JSONArray();
            for (Group group: groups)
            {
                JSONObject body = new JSONObject();
                body.put("groupID", group.getGroupID());
                body.put("groupName", group.getGroupName());
                jsonObjects.put(body);
            }
            return new ResponseEntity<>(jsonObjects.toString(), HttpStatus.FOUND);

        } catch (IllegalStateException ise) {
            // if the group is not found, report bad request
            return new ResponseEntity<>(ise.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (JSONException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // creating new group and communicate API response
    @PostMapping
    public ResponseEntity<String> addGroup(@RequestBody Group group)
    {
        try {
            groupService.addGroup(group);
            JSONObject body = new JSONObject();
            body.put("groupID", group.getGroupID());
            body.put("groupName", group.getGroupName());
            return new ResponseEntity<>(body.toString(), HttpStatus.CREATED);
        } catch (IllegalStateException ise) {
            // if the group already exists, report bad request (400)
            return new ResponseEntity<>(ise.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (JSONException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // updating group name
    @PutMapping("/{groupID}/{groupName}")
    public ResponseEntity<String> updateGroupName(@PathVariable("groupID") int groupID, @PathVariable("groupName") String groupName)
    {
        try
        {
            groupService.updateGroupName(groupID, groupName);
            JSONObject body = new JSONObject();
            body.put("groupID", groupID);
            body.put("groupName", groupName);
            return new ResponseEntity<>(body.toString(), HttpStatus.OK);
        } catch (IllegalStateException ise) {
            // if the group already exists, report bad request (400)
            return new ResponseEntity<>(ise.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (JSONException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
