package com.ldt.api.service.impl;

import com.ldt.api.dto.TaskDTO;
import com.ldt.api.entity.Task;
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

    /**
     * add task
     */
    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    /**
     * get tasks as list
     */
    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * get task by id
     */

    @Override
    public Task getTask(Integer id) {
//        check weather the task is in database or not
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        return task;
    }

    /**
     * update task
     */

    @Override
    public void updateTask(Integer id, Task task) {
//        check weather the task is in database or not
        taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        task.setId(id);

        taskRepository.save(task);

    }

    @Override
    public void deleteTask(Integer id) {
//        check weather the task is in database or not
        Task task = taskRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        taskRepository.delete(task);
    }

    @Override
    public void updateName(Integer id, TaskDTO taskDTO) {
//        check weather the task is in database or not
        Task task = taskRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        task.setName(taskDTO.getName());

        taskRepository.save(task);

    }
}
