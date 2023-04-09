package com.web.roomiez.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/groups")
public class GroupController {

    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService)
    {
        this.groupService = groupService;
    }

    @GetMapping("/{groupID}")
    public List<Group> getGroups()
    {
        return groupService.getGroups();
    }
}
