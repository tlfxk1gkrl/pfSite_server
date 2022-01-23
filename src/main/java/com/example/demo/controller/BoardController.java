package com.example.demo.controller;

import com.example.demo.domain.BoardDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //create
    @PostMapping("/board/create")
    public boolean createBoard(@RequestBody Map<String, Object> map){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle((String) map.get("title"));
        boardDTO.setTool((String) map.get("tool"));
        boardDTO.setPeriod(Integer.parseInt((String) map.get("period")));
        boardDTO.setSub((String) map.get("sub"));
        return boardService.createBoard(boardDTO);
    }

    //list
    @GetMapping("/board")
    @ResponseBody
    public List<BoardDTO> BoardList(){
        return boardService.getBoardList();
    }

    //read
    @GetMapping("/board/{id}")
    @ResponseBody
    public BoardDTO getBoard(@PathVariable("id") Integer id){
        return boardService.getBoardDetail(id);
    }
}
