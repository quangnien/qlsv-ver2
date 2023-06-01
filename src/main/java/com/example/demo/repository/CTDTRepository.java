package com.example.demo.repository;

import com.example.demo.entity.CTDTEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CTDTRepository extends MongoRepository<CTDTEntity, String> {
    int countCTDTByMaCTDT(String maCTDT);
    int countCTDTById(String id);

    CTDTEntity findByMaCTDT(String maCTDT);
}