package com.ldt.api.repository;

import com.ldt.api.entity.TaskColumn;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskColumnRepository extends JpaRepository<TaskColumn, Integer> {

  List<TaskColumn> findByBoardId(Integer boardId);
}
