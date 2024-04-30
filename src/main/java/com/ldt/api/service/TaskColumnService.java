package com.ldt.api.service;

import com.ldt.api.dto.TaskColumnDTO;
import com.ldt.api.entity.TaskColumn;

import java.util.List;

public interface TaskColumnService {
    void addTaskColumn(TaskColumn taskColumn);

    List<TaskColumn> getTaskColumns();

    TaskColumn getTaskColumn(Integer id);

    void updateTaskColumn(Integer id, TaskColumn taskColumn);

    void deleteTaskColumn(Integer id);

    void updateName(Integer id, TaskColumnDTO taskColumnDTO);
}
