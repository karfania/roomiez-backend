package com.web.roomiez.Task;
//THIS CLASS CALLS FUNCTIONS DEFINED IN TASK SERVICE
import com.web.roomiez.group.Group;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getTasks()
    {
        return taskService.getTasks();
    }
    @GetMapping("/{taskID}")
    public ResponseEntity<Task> getTaskByID(@PathVariable("taskID") int taskID) throws ChangeSetPersister.NotFoundException {
        Task task = taskService.getTaskById(taskID);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        String repeat = task.getRepeat();
        LocalDate currentDate = LocalDate.now();

        task.setStartDate(currentDate.toString());
        LocalDate incrementedDate = null;
        if(repeat.equals("daily")){
             incrementedDate = currentDate.plusDays(1);
        }
        else if(repeat.equals("weekly")){
            incrementedDate = currentDate.plusDays(7);
        }
        else if(repeat.equals("monthly")){
            incrementedDate = currentDate.plusMonths(1);
        }
        if(incrementedDate != null) {
            task.setEndDate(incrementedDate.toString());
        }
        Task createdTask = taskService.addTask(task);
        if (createdTask == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/progress/{taskId}")
    public ResponseEntity<Task> updateTaskProgress(@PathVariable("taskId") int taskId, @RequestBody int progress) throws ChangeSetPersister.NotFoundException {
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
