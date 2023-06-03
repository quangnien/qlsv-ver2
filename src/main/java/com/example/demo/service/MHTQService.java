package com.example.demo.service;

import com.example.demo.dto.MHMHTQDto;
import com.example.demo.entity.MHTQEntity;

import java.util.List;

public interface MHTQService {

    public MHMHTQDto updateExist(MHMHTQDto gvdowDto);
    public List<MHTQEntity> findAllByMaMH(String maMH);
    public MHTQEntity findById(String id);
    public List<MHTQEntity> findAll();

}