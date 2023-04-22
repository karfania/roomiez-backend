package com.web.roomiez.group;

import com.web.roomiez.Task.Task;
import com.web.roomiez.Task.TaskRepository;
import com.web.roomiez.user.User;
import com.web.roomiez.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String remind(String name, String task) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#66347F\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#66347F\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Complete your task</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#37306B\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Your roommate(s) would like to kindly remind you to finish your task: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <p>" + task + "</p> </p></blockquote>\n Please get to it soon! <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
