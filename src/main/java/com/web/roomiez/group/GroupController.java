package com.web.roomiez.group;

import com.web.roomiez.Task.Task;
import com.web.roomiez.Task.TaskService;
import com.web.roomiez.email.EmailService;
import com.web.roomiez.user.User;
import com.web.roomiez.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

// API LAYER
@RestController
@RequestMapping(path="/groups")
@CrossOrigin
public class GroupController {

    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService)
    {
        this.groupService = groupService;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;


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

    // creating new group (via JSON) and communicate API response
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/email")
    public ResponseEntity<String> notifyUser(@RequestBody Task task)
    {
        // key is task ID
        try
        {
            User taskUser = userService.findByUserID(task.getAssigneeID());
            JSONObject body = new JSONObject();
            body.put("taskID", task.getAssigneeID());
            body.put("user", taskUser);
            return new ResponseEntity<>(body.toString(), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // updating group name
    @PutMapping("/{groupID}")
    public ResponseEntity<String> updateGroupName(@PathVariable("groupID") int groupID, @RequestBody Group group)
    {
        Group updatedGroup = groupService.getGroupByID(groupID);
        updatedGroup.setGroupName(group.getGroupName());
        groupService.saveGroup(updatedGroup);

        JSONObject body = new JSONObject();
        body.put("groupID", updatedGroup.getGroupName());
        body.put("groupName", updatedGroup.getGroupName());

        return new ResponseEntity<>(updatedGroup.toString(),HttpStatus.OK);
    }

    // getting users within a group
    @GetMapping("/{groupID}/users")
    public ResponseEntity<String> getUsersInGroup(@PathVariable("groupID") int groupID)
    {
        try
        {
            List<User> usersInGroup = groupService.getUsersInGroup(groupID);
            List<JSONObject> listJSON = new ArrayList<>();

            for (User user : usersInGroup)
            {
                listJSON.add(user.toJSON());
            }

            // body creation
            JSONObject body = new JSONObject();
            body.put("groupID", groupID);
            body.put("users", listJSON);
            return new ResponseEntity<>(body.toString(), HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // getting tasks assigned to a group
    @GetMapping("/{groupID}/tasks")
    public ResponseEntity<String> getGroupTasks(@PathVariable("groupID") int groupID)
    {
        try
        {
            List<Task> groupTasks = groupService.getGroupTasks(groupID);

            // body creation
            JSONObject body = new JSONObject();
            body.put("groupID", groupID);
            body.put("tasks", groupTasks);
            return new ResponseEntity<>(body.toString(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all users and their tasks in a group
    @GetMapping("/{groupID}/userTasks")
    public ResponseEntity<String> getGroupUserTasks(@PathVariable("groupID") int groupID)
    {
        JSONObject mainBody = new JSONObject();
        try
        {
            List<User> usersInGroup = groupService.getUsersInGroup(groupID);
            JSONObject userTaskJSON = new JSONObject();
            for (User user: usersInGroup)
            {
                List<Task> usersTasks = taskService.getTasksForUser(user.getID());
                userTaskJSON.put(user.getName(),usersTasks);
            }

            mainBody.put("roommates",userTaskJSON);
            return new ResponseEntity<>(mainBody.toString(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // sending an email to a user within a group
    @GetMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestParam String username, @RequestParam String taskIn)
    {
        int taskID = Integer.parseInt(taskIn);
        try {
            Task task = taskService.getTaskById(taskID);
            String emailBody = groupService.remind(username, task.toString());
            emailService.send(username, emailBody);

            return new ResponseEntity<>("Email sent to: " + username, HttpStatus.OK);


        } catch (ChangeSetPersister.NotFoundException nfe) {
            return new ResponseEntity<>("Could not find taskID: " + taskID, HttpStatus.NOT_FOUND);
        }


    }

    // deleting group via their ID
    @DeleteMapping("/{groupID}")
    public ResponseEntity<String> deleteGroupByID(@PathVariable("groupID") int groupID)
    {
        try {
            // first, grab all of the tasks assigned to the group and delete them
            List<Task> tasksToRemove = groupService.getGroupTasks(groupID);
            for (Task task: tasksToRemove)
            {
                taskService.deleteTaskByID(task.getID());
            }

            userService.deleteGroupID(groupID);

            // finally, delete the group
            boolean deleted = groupService.deleteGroupByID(groupID);
            // if we didn't delete anything, return error
            if (!deleted)
            {
                return new ResponseEntity<>("Could not delete group " + groupID + ".", HttpStatus.NOT_FOUND);
            }
            // otherwise, deleted
            return new ResponseEntity<>("Deleted group " + groupID + ".", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
