package com.example.demo.service;

import com.example.demo.dto.GiangDayDto;
import com.example.demo.entity.GiangDayEntity;

import java.util.List;

public interface GiangDayService {
    public GiangDayDto updateExist(GiangDayDto giangDayDto);
    public List<GiangDayEntity> findAllByMaGV(String maGV);
    public List<GiangDayEntity> findAllByMaMH(String maMH);
    public GiangDayEntity findById(String id);
    public void deleteRecord(String id);
}