package com.example.demo.repository;

import com.example.demo.entity.LopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LopRepository extends MongoRepository<LopEntity, String> {
    int countLopByMaLop(String maLop);
    int countLopById(String id);

    LopEntity findByMaLop(String maLop);
}