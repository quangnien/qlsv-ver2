package com.example.demo.service;

import com.example.demo.entity.CTDTEntity;

import java.util.List;

public interface CTDTService {
    public CTDTEntity addCTDT(CTDTEntity ctdtEntity);
    public List<CTDTEntity> findAllCTDT();
    public CTDTEntity findById(String id);
    public CTDTEntity findByMaCTDT(String maCTDT);
}
