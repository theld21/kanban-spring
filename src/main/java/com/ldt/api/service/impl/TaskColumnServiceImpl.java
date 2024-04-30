package com.ldt.api.service.impl;

import com.ldt.api.dto.TaskColumnDTO;
import com.ldt.api.entity.TaskColumn;
import com.ldt.api.repository.TaskColumnRepository;
import com.ldt.api.service.TaskColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskColumnServiceImpl implements TaskColumnService {
    @Autowired
    private TaskColumnRepository taskColumnRepository;

    /**
     * add taskColumn
     */
    @Override
    public void addTaskColumn(TaskColumn taskColumn) {
        taskColumnRepository.save(taskColumn);
    }

    /**
     * get taskColumns as list
     */
    @Override
    public List<TaskColumn> getTaskColumns() {
        return taskColumnRepository.findAll();
    }

    /**
     * get taskColumn by id
     */

    @Override
    public TaskColumn getTaskColumn(Integer id) {
//        check weather the taskColumn is in database or not
        TaskColumn taskColumn = taskColumnRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        return taskColumn;
    }

    /**
     * update taskColumn
     */

    @Override
    public void updateTaskColumn(Integer id, TaskColumn taskColumn) {
//        check weather the taskColumn is in database or not
        taskColumnRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        taskColumn.setId(id);

        taskColumnRepository.save(taskColumn);

    }

    @Override
    public void deleteTaskColumn(Integer id) {
//        check weather the taskColumn is in database or not
        TaskColumn taskColumn = taskColumnRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        taskColumnRepository.delete(taskColumn);
    }

    @Override
    public void updateName(Integer id, TaskColumnDTO taskColumnDTO) {
//        check weather the taskColumn is in database or not
        TaskColumn taskColumn = taskColumnRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        taskColumn.setName(taskColumnDTO.getName());

        taskColumnRepository.save(taskColumn);

    }
}
