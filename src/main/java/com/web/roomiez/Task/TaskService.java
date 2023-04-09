package com.web.roomiez.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//THIS CLASS DEFINES FUNCTIONS
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void addTask(Task task) {
        taskRepository.save(task);
    }
    public String hello(){
        return "hello";
    }

    //Create a new task and add it to the database
}
