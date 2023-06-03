package com.example.demo.service;

import com.example.demo.entity.CTDTEntity;

import java.util.List;

public interface CTDTService {
    public CTDTEntity addNew(CTDTEntity ctdtEntity);
    public List<CTDTEntity> findAll();
    public CTDTEntity findById(String id);
    public CTDTEntity findByMaCTDT(String maCTDT);
}
