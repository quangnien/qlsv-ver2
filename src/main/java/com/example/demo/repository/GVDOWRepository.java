package com.example.demo.repository;

import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.entity.GvDowEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GVDOWRepository extends MongoRepository<GvDowEntity, String> {
//    int countDayOfWeekByMaDOW(String maDOW);
//    int countDayOfWeekById(String id);

    @Query(value = "{maGV: ?0, maDOW: ?1 }", count = true)
    Long countByMaSvAndMaLopTc(String maGV, String maDOW);

    List<GvDowEntity> findAllByMaGV(String maGV);
    List<GvDowEntity> findAllByMaDOW(String maDOW);
}