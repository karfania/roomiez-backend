package com.web.roomiez.Task;
//THIS CLASS CALLS FUNCTIONS DEFINED IN TASK SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") int taskId) throws ChangeSetPersister.NotFoundException {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task createdTask = taskService.addTask(task);
        if (createdTask == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/progress/{taskId}")
    public ResponseEntity<Task> updateTaskProgress(@PathVariable("taskId") int taskId, @RequestBody Progress progress) throws ChangeSetPersister.NotFoundException {
        Task updatedTask = taskService.updateTaskProgress(taskId, progress);
        if (updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/endDate/{taskId}")
    public ResponseEntity<Task> updateTaskEndDate(@PathVariable("taskId") int taskId, @RequestBody String endDate) throws ChangeSetPersister.NotFoundException {
        Task updatedTask = taskService.updateTaskEndDate(taskId, endDate);
        if (updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/endTime/{taskId}")
    public ResponseEntity<Task> updateTaskEndTime(@PathVariable("taskId") int taskId, @RequestBody String endTime) throws ChangeSetPersister.NotFoundException {
        Task updatedTask = taskService.updateTaskEndTime(taskId, endTime);
        if (updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskByID(@PathVariable("taskId") int taskId) {
        boolean deleted = taskService.deleteTaskByID(taskId);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
