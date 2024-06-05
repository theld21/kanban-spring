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

        User user = SecurityUtil.getCurrentUser();
        task.setUserId(user.getId());
        task.setTaskColumn(taskColumn);

        taskRepository.save(task);
    }

    /**
     * get tasks as list
     */
    @Override
    public List<Task> findByTaskColumnId(Integer taskColumnId) {
        TaskColumn taskColumn = taskColumnRepository
                .findById(taskColumnId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        SecurityUtil.checkAuthor(taskColumn.getUserId());

        return taskRepository.findByTaskColumnId(taskColumnId);
    }

    /**
     * get task by id
     */

    @Override
    public Task getTask(Integer id) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid task Id:" + id));

        SecurityUtil.checkAuthor(task.getUserId());

        return taskColumn;
    }

    /**
     * update task
     */

    @Override
    public void updateTask(Integer id, Task task) {
        task.setId(id);
        Task oldTask = this.getTask(id)
        task.setUserId(oldTask.getUserId());

        TaskColumn taskColumn = this.getTaskColumn(task.getTaskColumnId())
        task.setTaskColumn(taskColumn);

        taskRepository.save(task);

    }

    /**
     * move task
     */

    @Override
    public void moveTask(Integer id, Task task) {
        Task oldTask = this.getTask(id);
        TaskColumn taskColumn = this.getTaskColumn(task.getTaskColumnId())
        task.setTaskColumn(taskColumn);

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = this.getTask(id);
        taskRepository.delete(task);
    }

    @Override
    public void updateName(Integer id, TaskDTO taskDTO) {
        Task task = this.getTask(id);
        task.setName(taskDTO.getName());

        taskRepository.save(task);

    }
}
