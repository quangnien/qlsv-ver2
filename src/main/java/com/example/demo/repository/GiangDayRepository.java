package com.example.demo.repository;

import com.example.demo.entity.GiangDayEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GiangDayRepository extends MongoRepository<GiangDayEntity, String> {

    @Query(value = "{maGV: ?0, maMH: ?1 }", count = true)
    Long countByMaGVAndMaMH(String maGV, String maMH);

    int countByMaGV(String maGV);

    List<GiangDayEntity> findAllByMaGV(String maGV);
    List<GiangDayEntity> findAllByMaMH(String maMH);
}