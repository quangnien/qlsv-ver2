package com.example.demo.repository;

import com.example.demo.entity.TichLuyEntity;
import com.example.demo.entity.TichLuyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TichLuyRepository extends MongoRepository<TichLuyEntity, String> {

    @Query(value = "{maCTDT: ?0, maMH: ?1 }", count = true)
    Long countByMaCTDTAndMaMH(String maCTDT, String maMH);

    List<TichLuyEntity> findAllByMaCTDT(String maCTDT);
    List<TichLuyEntity> findAllByMaMH(String maMH);
}