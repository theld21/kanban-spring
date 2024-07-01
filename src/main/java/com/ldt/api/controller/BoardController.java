package com.ldt.api.controller;

import com.ldt.api.dto.BoardDTO;
import com.ldt.api.entity.Board;
import com.ldt.api.security.ResponseHelper;
import com.ldt.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * add board
     */

    @PostMapping("/add")
    public ResponseEntity addBoard(@RequestBody Board board) {
        boardService.addBoard(board);

        return ResponseHelper.responseMsg("success add board", HttpStatus.OK);
    }

    /**
     * get boards as list
     */

    @GetMapping
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    /**
     * get board by id
     */

    @GetMapping("/get")
    public Board getBoard(@RequestParam Integer id) {
        return boardService.getBoard(id);
    }

    /**
     * update board
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Integer id, @RequestBody Board board) {
        boardService.updateBoard(id, board);

        return ResponseEntity.noContent().build();
    }

    /**
     * delete board
     */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * update name
     */

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Integer id, @RequestBody BoardDTO boardDTO) {
        boardService.updateName(id, boardDTO);

        return ResponseEntity.noContent().build();
    }

}
