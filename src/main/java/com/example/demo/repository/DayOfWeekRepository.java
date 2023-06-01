package com.example.demo.repository;

import com.example.demo.entity.DayOfWeekEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DayOfWeekRepository extends MongoRepository<DayOfWeekEntity, String> {
    int countDayOfWeekByMaDOW(String maDOW);
    int countDayOfWeekById(String id);
    
    DayOfWeekEntity findByMaDOW(String maDOW);
}