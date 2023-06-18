package com.example.demo.service;

import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.entity.MonHocEntity;

import java.util.List;

public interface DayOfWeekService {
    public DayOfWeekEntity addNew(DayOfWeekEntity dayOfWeekEntity);
    public List<DayOfWeekEntity> findAll();
    public DayOfWeekEntity findById(String id);
    public DayOfWeekEntity findByMaDOW(String maDOW);
    public List<DayOfWeekEntity> findAllByMaGV(String maGV);

}