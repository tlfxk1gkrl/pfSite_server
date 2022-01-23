package com.example.demo.service;

import com.example.demo.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    public boolean createBoard(BoardDTO params);
    public BoardDTO getBoardDetail(Integer id);
    public boolean deleteBoard(Integer id);
    public List<BoardDTO> getBoardList();
}
