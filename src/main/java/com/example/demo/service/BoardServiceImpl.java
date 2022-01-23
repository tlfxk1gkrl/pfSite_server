package com.example.demo.service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean createBoard(BoardDTO params) {
        int queryResult = 0;
        if(params.getId() == null){
            queryResult = boardMapper.insertBoard(params);
        }else{
            queryResult = boardMapper.updateBoard(params);
        }

        return (queryResult==1) ?true:false;
    }

    @Override
    public BoardDTO getBoardDetail(Integer id) {
        return boardMapper.selectBoardDetail(id);
    }

    @Override
    public boolean deleteBoard(Integer id) {
        int queryResult = boardMapper.deleteBoard(id);

        return (queryResult==1)?true:false;
    }

    @Override
    public List<BoardDTO> getBoardList() {
        return boardMapper.selectBoardList();
    }
}
