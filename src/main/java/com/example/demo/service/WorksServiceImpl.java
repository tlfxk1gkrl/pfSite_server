package com.example.demo.service;

import com.example.demo.domain.WorksDTO;
import com.example.demo.mapper.WorksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksServiceImpl implements WorksService{

    @Autowired
    private WorksMapper WorksMapper;

    @Override
    public boolean registerWorks(WorksDTO params) {
        int queryResult = 0;
        if(params.getId() == null){
            queryResult = WorksMapper.insertWorks(params);
        }else{
            queryResult = WorksMapper.updateWorks(params);
        }

        return (queryResult==1) ?true:false;
    }

    @Override
    public WorksDTO getWorksDetail(Integer id) {
        return WorksMapper.selectWorksDetail(id);
    }

    @Override
    public boolean deleteWorks(Integer id) {
        return false;
    }

    @Override
    public List<WorksDTO> getWorksList() {
        return WorksMapper.selectWorksList();
    }
}
