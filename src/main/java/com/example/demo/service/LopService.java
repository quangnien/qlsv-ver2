package com.example.demo.service;

import com.example.demo.entity.LopEntity;

import java.util.List;

public interface LopService {
    public LopEntity addNew(LopEntity lopEntity);
    public List<LopEntity> findAll();
    public LopEntity findById(String id);
    public LopEntity findByMaLop(String maLop);
}
