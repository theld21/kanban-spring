package com.ldt.api.service.impl;

import com.ldt.api.dto.BoardDTO;
import com.ldt.api.entity.Board;
import com.ldt.api.entity.User;
import com.ldt.api.repository.BoardRepository;
import com.ldt.api.service.BoardService;
import com.ldt.api.util.SecurityUtil;

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
    public void addBoard(Board board) {
        User user = SecurityUtil.getCurrentUser();
        board.setUserId(user.getId());
        boardRepository.save(board);
    }

    /**
     * get boards as list
     */
    @Override
    public List<Board> getBoards() {
        User user = SecurityUtil.getCurrentUser();
        return boardRepository.findByUserId(user.getId());
    }

    /**
     * get Board by id
     */
    @Override
    public Board getBoard(Integer id) {
        Board board = boardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Board Id:" + id));

        SecurityUtil.checkAuthor(board.getUserId());
        return board;
    }

    /**
     * update Board
     */
    @Override
    public void updateBoard(Integer id, Board board) {
        this.getBoard(id);
        board.setId(id);

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Integer id) {
        Board board = this.getBoard(id);
        boardRepository.delete(board);
    }

    @Override
    public void updateName(Integer id, BoardDTO boardDTO) {
        Board Board = this.getBoard(id);
        Board.setName(boardDTO.getName());

        boardRepository.save(Board);

    }
}
