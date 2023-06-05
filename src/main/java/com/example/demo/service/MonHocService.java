package com.example.demo.service;

import com.example.demo.entity.MonHocEntity;

import java.util.List;

public interface MonHocService {
    
    //    public List<MonHocEntity> findAllByMaLop(String maLop, int page, int size);
    //    public MonHocEntity getByMaMH(String maMh);
    
    List<MonHocEntity> findByTenMHContainingIgnoreCaseLike(String keySearch);

    public MonHocEntity addNew(MonHocEntity monHocEntity);
    public MonHocEntity updateExist(MonHocEntity monHocEntity);
    public List<MonHocEntity> findAll();
    public MonHocEntity findById(String id);
    public MonHocEntity findByMaMH(String maMH);
    public List<String> deleteList(List<String> lstId);

    public List<MonHocEntity> findAllByMaLop(String maLop);

}