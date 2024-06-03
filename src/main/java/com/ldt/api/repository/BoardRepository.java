package com.ldt.api.repository;

import com.ldt.api.entity.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
  List<Board> findByUserId(Integer userId);
}
