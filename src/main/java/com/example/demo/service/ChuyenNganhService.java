package com.example.demo.service;

import com.example.demo.entity.ChuyenNganhEntity;

import java.util.List;

public interface ChuyenNganhService {
    public ChuyenNganhEntity addChuyenNganh(ChuyenNganhEntity chuyenNganhEntity);
    public List<ChuyenNganhEntity> findAllChuyenNganh();
    public ChuyenNganhEntity findById(String id);
    public ChuyenNganhEntity findByMaCN(String maCN);
}
