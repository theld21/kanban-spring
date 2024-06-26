package com.ldt.api.service.impl;

import com.ldt.api.dto.TaskColumnDTO;
import com.ldt.api.entity.Board;
import com.ldt.api.entity.TaskColumn;
import com.ldt.api.entity.User;
import com.ldt.api.repository.BoardRepository;
import com.ldt.api.repository.TaskColumnRepository;
import com.ldt.api.service.TaskColumnService;
import com.ldt.api.util.SecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskColumnServiceImpl implements TaskColumnService {
    @Autowired
    private TaskColumnRepository taskColumnRepository;
    @Autowired
    private BoardRepository boardRepository;

    /**
     * add taskColumn
     */
    @Override
    public void addTaskColumn(TaskColumn taskColumn) {
        Board board = boardRepository.findById(taskColumn.getBoardId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:"));

        User user = SecurityUtil.getCurrentUser();
        taskColumn.setUserId(user.getId());
        taskColumn.setBoard(board);

        taskColumnRepository.save(taskColumn);
    }

    /**
     * get taskColumns as list
     */
    @Override
    public List<TaskColumn> findByBoardId(Integer boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + boardId));

        SecurityUtil.checkAuthor(board.getUserId());

        return taskColumnRepository.findByBoardId(boardId);
    }

    /**
     * get taskColumn by id
     */

    @Override
    public TaskColumn getTaskColumn(Integer id) {
        TaskColumn taskColumn = taskColumnRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid taskColumn Id:" + id));

        SecurityUtil.checkAuthor(taskColumn.getUserId());

        return taskColumn;
    }

    /**
     * update taskColumn
     */

    @Override
    public void updateTaskColumn(Integer id, TaskColumn taskColumn) {
        TaskColumn oldTaskColumn = this.getTaskColumn(id);
        taskColumn.setUserId(oldTaskColumn.getUserId());

        taskColumnRepository.save(taskColumn);

    }

    @Override
    public void deleteTaskColumn(Integer id) {
        TaskColumn taskColumn = this.getTaskColumn(id);
        taskColumnRepository.delete(taskColumn);
    }

    @Override
    public void updateName(Integer id, TaskColumnDTO taskColumnDTO) {
        TaskColumn taskColumn = this.getTaskColumn(id);
        taskColumn.setName(taskColumnDTO.getName());

        taskColumnRepository.save(taskColumn);

    }
}
