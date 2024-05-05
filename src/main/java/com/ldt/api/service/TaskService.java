package com.ldt.api.service;

import com.ldt.api.dto.TaskDTO;
import com.ldt.api.entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);

    List<Task> findByTaskColumnId(Integer taskColumnId);

    Task getTask(Integer id);

    void updateTask(Integer id, Task task);

    void moveTask(Integer id, Task task);

    void deleteTask(Integer id);

    void updateName(Integer id, TaskDTO taskDTO);
}
