package com.ldt.api.repository;

import com.ldt.api.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  List<Task> findByTaskColumnId(Integer taskColumnId);
}
