package com.example.demo.service;

import com.example.demo.entity.DayOfWeekEntity;

import java.util.List;

public interface DayOfWeekService {
    public DayOfWeekEntity addDayOfWeek(DayOfWeekEntity dayOfWeekEntity);
    public List<DayOfWeekEntity> findAll();
    public DayOfWeekEntity findById(String id);
    public DayOfWeekEntity findByMaDOW(String maDOW);
}