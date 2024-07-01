package com.ldt.api.controller;

import com.ldt.api.dto.TaskDTO;
import com.ldt.api.entity.Task;
import com.ldt.api.security.ResponseHelper;
import com.ldt.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * add task
     */

    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody Task task) {
        taskService.addTask(task);

        return ResponseHelper.responseMsg("success add task", HttpStatus.OK);
    }

    /**
     * get tasks as list
     */

    @GetMapping
    public List<Task> getTasks(@RequestParam Integer taskColumnId) {
        return taskService.findByTaskColumnId(taskColumnId);
    }

    /**
     * get task by id
     */

    @GetMapping("/get")
    public Task getTask(@RequestParam Integer id) {
        return taskService.getTask(id);
    }

    /**
     * update task
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        taskService.updateTask(id, task);

        return ResponseEntity.noContent().build();
    }

    /**
     * update task
     */

    @PutMapping("/move/{id}")
    public ResponseEntity<Void> moveTask(@PathVariable Integer id, @RequestBody Task task) {
        taskService.moveTask(id, task);

        return ResponseEntity.noContent().build();
    }

    /**
     * delete task
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * update name
     */

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        taskService.updateName(id, taskDTO);

        return ResponseEntity.noContent().build();
    }

}
