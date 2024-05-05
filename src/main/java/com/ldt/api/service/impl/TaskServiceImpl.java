package com.ldt.api.service.impl;

import com.ldt.api.dto.TaskDTO;
import com.ldt.api.entity.Task;
import com.ldt.api.entity.TaskColumn;
import com.ldt.api.repository.TaskColumnRepository;
import com.ldt.api.repository.TaskRepository;
import com.ldt.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskColumnRepository taskColumnRepository;

    /**
     * add task
     */
    @Override
    public void addTask(Task task) {
        TaskColumn taskColumn = taskColumnRepository.findById(task.getTaskColumnId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Task Column Id:"));

        task.setTaskColumn(taskColumn);

        taskRepository.save(task);
    }

    /**
     * get tasks as list
     */
    @Override
    public List<Task> findByTaskColumnId(Integer taskColumnId) {
        return taskRepository.findByTaskColumnId(taskColumnId);
    }

    /**
     * get task by id
     */

    @Override
    public Task getTask(Integer id) {
        // check weather the task is in database or not
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));
    }

    /**
     * update task
     */

    @Override
    public void updateTask(Integer id, Task task) {
        // check weather the task is in database or not
        taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        task.setId(id);

        TaskColumn taskColumn = taskColumnRepository.findById(task.getTaskColumnId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Task Column Id:"));

        task.setTaskColumn(taskColumn);

        taskRepository.save(task);

    }

    /**
     * move task
     */

    @Override
    public void moveTask(Integer id, Task task) {
        // check weather the task is in database or not
        Task taskUpdate = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        taskUpdate.setTaskColumn(taskColumnRepository.findById(task.getTaskColumnId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Task Column Id:")));

        taskRepository.save(taskUpdate);

    }

    @Override
    public void deleteTask(Integer id) {
        // check weather the task is in database or not
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        taskRepository.delete(task);
    }

    @Override
    public void updateName(Integer id, TaskDTO taskDTO) {
        // check weather the task is in database or not
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        task.setName(taskDTO.getName());

        taskRepository.save(task);

    }
}
