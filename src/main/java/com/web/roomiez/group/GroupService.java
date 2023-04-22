package com.web.roomiez.group;

import com.web.roomiez.Task.Task;
import com.web.roomiez.Task.TaskRepository;
import com.web.roomiez.Task.TaskService;
import com.web.roomiez.user.User;
import com.web.roomiez.user.UserRepository;
import com.web.roomiez.user.UserService;
import org.hibernate.service.NullServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// BUSINESS LOGIC LAYER
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository)
    {
        this.groupRepository = groupRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

//    @Autowired
//    private Group group;
//
//    @Autowired
//    private Task task;

    public void saveGroup(Group group)
    {
        groupRepository.save(group);
    }

    // return list of all groups in our database
    public List<Group> getGroups()
    {
       return groupRepository.findAll();
    }

    // return the specific group given their groupID
    public Group getGroupByID(int groupID)
    {
        Optional<Group> groupByID = groupRepository.findGroupByID(groupID);
        if (groupByID.isPresent())
        {
            return groupByID.get();
        }
        // if we can't find the group, throw an exception
        else
        {
            throw new IllegalStateException("Group " + groupID + " could not be found.");
        }
    }

    // return the list of groups given their groupName
    public List<Group> getGroupsByName(String groupName)
    {
        List<Group> groupsByName = groupRepository.findGroupsByName(groupName);
        if(groupsByName != null && groupsByName.size() > 0)
        {
            return groupsByName;
        }
        // if we can't find the group, throw an exception
        else
        {
            throw new IllegalStateException("No groups with name " + groupName + ".");
        }
    }

    // save group to database unless it already exists
    public void addGroup(Group group)
    {
        Optional<Group> optionalGroup = groupRepository.findGroupByID(group.getGroupID());
        // if the group already exists, throw exception
        if (optionalGroup.isPresent())
        {
            throw new IllegalStateException("Group " + group.getGroupID() + " already exists.");
        }
        //otherwise, save the student
        groupRepository.save(group);

    }

    // update group name if it exists
    public void updateGroup(int groupID, Group group)
    {
        groupRepository.updateGroupNameByID(groupID, group);
        Optional<Group> optionalUpdatedGroup = groupRepository.findGroupByID(groupID);
        // if the group exists and the name was modified, save the updated student
        if (optionalUpdatedGroup.isPresent())
        {
            groupRepository.save(optionalUpdatedGroup.get());
        }
        // if that group doesn't exist, throw an error
        else
        {
            throw new IllegalStateException("Group " + groupID + " could not be found.");
        }
    }

    // get users in group with group ID
    public List<User> getUsersInGroup(int groupID) throws Exception
    {
        List<User> usersInGroup = userRepository.findBygroupID(groupID);
        //List<User> usersInGroup = groupRepository.findByUser_groupID(groupID);
        // if we receive null, it means we have an error in access
        if (usersInGroup == null)
        {
            throw new Exception("No users in group with groupID: " + groupID + ".");
        }

       // group.setUsersInGroup(usersInGroup);
        return usersInGroup;
    }

    // get tasks assigned to a group
    public List<Task> getGroupTasks(int groupID) throws Exception
    {
        List<Task> groupTasks = taskRepository.findByGroup_groupID(groupID);
        // if we receive null, it means we have an error in access
        if (groupTasks == null) {
            throw new Exception("No users in group with groupID: " + groupID + ".");
        }
        //group.setGroupTasks(groupTasks);
        return groupTasks;
    }

    // deleting group
    public boolean deleteGroupByID(int groupID)
    {
        try
        {
            groupRepository.deleteById(groupID);
            return true;
        } catch (Exception e)
        {
            // if there is an issue, we didn't remove the group
            return false;
        }
    }
}
