package com.ldt.api.controller;

import com.ldt.api.dto.TaskColumnDTO;
import com.ldt.api.entity.TaskColumn;
import com.ldt.api.service.TaskColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/column")
public class TaskColumnController {

    @Autowired
    private TaskColumnService taskColumnService;

    /**
     * add taskColumn
     */

    @PostMapping("/add")
    public String addTaskColumn(@RequestBody TaskColumn taskColumn) {
        taskColumnService.addTaskColumn(taskColumn);

        return "success add taskColumn";
    }

    /**
     * get taskColumns as list
     */

    @GetMapping
    public List<TaskColumn> getTaskColumns(@RequestParam Integer boardId) {
        return taskColumnService.findByBoardId(boardId);
    }

    /**
     * get taskColumn by id
     */

    @GetMapping("/get")
    public TaskColumn getTaskColumn(@RequestParam Integer id) {
        return taskColumnService.getTaskColumn(id);
    }

    /**
     * update taskColumn
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTaskColumn(@PathVariable Integer id, @RequestBody TaskColumn taskColumn) {
        taskColumnService.updateTaskColumn(id, taskColumn);

        return ResponseEntity.noContent().build();
    }

    /**
     * delete taskColumn
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTaskColumn(@PathVariable Integer id) {
        taskColumnService.deleteTaskColumn(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * update name
     */

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody TaskColumnDTO taskColumnDTO) {
        taskColumnService.updateName(id, taskColumnDTO);

        return ResponseEntity.noContent().build();
    }

}
