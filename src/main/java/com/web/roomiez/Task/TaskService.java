package com.web.roomiez.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//THIS CLASS DEFINES FUNCTIONS
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {return taskRepository.findAll();}

    public Task addTask(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task getTaskById(int taskId) throws NotFoundException { //POTENTIALLY COULD CAUSE ERRORS
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            // handle the case when the task is not found
            throw new NotFoundException();
        }
    }

    public void createTask(int ID, int assigneeID, String assigneeName, int groupID, String name, String startDate, String endDate, String startTime, String endTime,
                           int progress, String description, String repeatTask){
        Task task = new Task(ID, assigneeID, assigneeName, groupID, name, startDate, endDate, startTime, endTime, progress, description, repeatTask);
        taskRepository.save(task);
    }

    public Task updateTaskProgress(int taskID, int progress) throws NotFoundException {
        taskRepository.updateTaskProgress(taskID, progress);
        return getTaskById(taskID);
    }

    public Task updateTaskEndDate(int taskID, String endDate) throws NotFoundException {
        taskRepository.updateTaskEndDate(taskID, endDate);
        return getTaskById(taskID);
    }

    public Task updateTaskEndTime(int taskID, String endTime) throws NotFoundException {
        taskRepository.updateTaskEndTime(taskID, endTime);
        return getTaskById(taskID);
    }

    public boolean deleteTaskByID(int ID){
        try {
            taskRepository.deleteById(ID);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //Get task repeat with task ID
//    public String getTaskRepeatWithTaskID(int taskID){
//        return taskRepository.getTaskRepeatWithTaskID(taskID);
//    }

    //Get group ID with username
    public int getGroupIDWithUsername(String username){
        return taskRepository.getGroupIDWithUsername(username);
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }
}
