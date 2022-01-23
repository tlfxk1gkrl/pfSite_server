package com.example.demo.mapper;

import com.example.demo.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int insertBoard(BoardDTO params);

    //pk 전달 받아 사용
    public BoardDTO selectBoardDetail(Integer id);
    public int updateBoard(BoardDTO params);
    public int deleteBoard(Integer id);
    public List<BoardDTO> selectBoardList();
    public int selectBoardTotalCount();
}
