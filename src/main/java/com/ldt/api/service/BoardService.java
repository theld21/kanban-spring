package com.ldt.api.service;

import com.ldt.api.dto.BoardDTO;
import com.ldt.api.entity.Board;

import java.util.List;

public interface BoardService {
    void addBoard(Board Board);

    List<Board> getBoards();

    Board getBoard(Integer id);

    void updateBoard(Integer id, Board Board);

    void deleteBoard(Integer id);

    void updateName(Integer id, BoardDTO boardDTO);
}
