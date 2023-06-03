package com.example.demo.service;

import com.example.demo.dto.GVDOWDto;
import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.entity.GvDowEntity;

import java.util.List;

public interface GVDOWService {
    public GVDOWDto updateExist(GVDOWDto gvdowDto);
    public List<GvDowEntity> findAllByMaGV(String maGV);
    public List<GvDowEntity> findAllByMaDOW(String maDOW);
    public GvDowEntity findById(String id);
}