package com.web.roomiez.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//THIS CLASS DEFINES FUNCTIONS
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

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

    public void createTask(int ID, int assigneeID, int groupID, String name, String startDate, String endDate, String startTime, String endTime,
                           Progress progress, String description){
        Task task = new Task(ID, assigneeID, groupID, name, startDate, endDate, startTime, endTime, progress, description);
        taskRepository.save(task);
    }

    public Task updateTaskProgress(int taskID, Progress progress) throws NotFoundException {
        taskRepository.updateTaskProgress(taskID, progress.getValue());
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

    public void deleteTaskByID(int ID){
        taskRepository.deleteById(ID);
    }

    public void deleteTask(Task task){
        taskRepository.delete(task);
    }
    public String hello(){
        return "hello";
    }

    //Create a new task and add it to the database
}
