package com.example.demo.service;

import com.example.demo.entity.ChuyenNganhEntity;

import java.util.List;

public interface ChuyenNganhService {
    public ChuyenNganhEntity addNew(ChuyenNganhEntity chuyenNganhEntity);
    public List<ChuyenNganhEntity> findAll();
    public ChuyenNganhEntity findById(String id);
    public ChuyenNganhEntity findByMaCN(String maCN);
}
