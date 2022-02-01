package com.example.demo.service;

import com.example.demo.domain.WorksDTO;

import java.util.List;

public interface WorksService {
    public boolean registerWorks(WorksDTO params);
    public WorksDTO getWorksDetail(Integer id);
    public boolean deleteWorks(Integer id);
    public List<WorksDTO> getWorksList();
}
