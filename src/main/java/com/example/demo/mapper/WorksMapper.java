package com.example.demo.mapper;

import com.example.demo.domain.WorksDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorksMapper {
    public int insertWorks(WorksDTO params);
    //pk 전달 받아 사용
    public WorksDTO selectWorksDetail(Integer id);
    public int updateWorks(WorksDTO params);
    public int deleteWorks(Integer id);
    public List<Integer> selectWorksList();
    public int selectWorksTotalCount();
}
