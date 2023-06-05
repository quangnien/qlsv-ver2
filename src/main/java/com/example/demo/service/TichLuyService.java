package com.example.demo.service;

import com.example.demo.entity.TichLuyEntity;

import java.util.List;

public interface TichLuyService {
    public void updateExist(List<TichLuyEntity> tichLuyEntityList, String tenCTDT);
    public List<TichLuyEntity> findAllByMaCTDT(String maCTDT);
    public List<TichLuyEntity> findAllByMaMH(String maMH);
    public TichLuyEntity findById(String id);

}