package com.ldt.api.service.impl;

import com.ldt.api.dto.BoardDTO;
import com.ldt.api.entity.Board;
import com.ldt.api.repository.BoardRepository;
import com.ldt.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    /**
     * add Board
     */
    @Override
    public void addBoard(Board Board) {
        boardRepository.save(Board);
    }

    /**
     * get boards as list
     */
    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    /**
     * get Board by id
     */

    @Override
    public Board getBoard(Integer id) {
        // check weather the Board is in database or not
        return boardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + id));
    }

    /**
     * update Board
     */

    @Override
    public void updateBoard(Integer id, Board Board) {
        // check weather the Board is in database or not
        boardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + id));

        Board.setId(id);

        boardRepository.save(Board);

    }

    @Override
    public void deleteBoard(Integer id) {
        // check weather the Board is in database or not
        Board Board = boardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + id));

        boardRepository.delete(Board);
    }

    @Override
    public void updateName(Integer id, BoardDTO boardDTO) {
        // check weather the Board is in database or not
        Board Board = boardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + id));

        Board.setName(boardDTO.getName());

        boardRepository.save(Board);

    }
}
