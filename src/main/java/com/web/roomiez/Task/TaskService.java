package com.web.roomiez.Task;

import com.web.roomiez.group.Group;
import com.web.roomiez.user.User;
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

    public void createTask(int ID, User assignee, Group group, String name, String startDate, String endDate, String startTime, String endTime,
                           int progress, String description){
        Task task = new Task(ID, assignee, group, name, startDate, endDate, startTime, endTime, progress, description);
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

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }
}
