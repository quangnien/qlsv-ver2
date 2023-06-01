package com.example.demo.repository;

import com.example.demo.entity.ChuyenNganhEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChuyenNganhRepository extends MongoRepository<ChuyenNganhEntity, String> {
    int countChuyenNganhByMaCN(String maCN);
    int countChuyenNganhById(String id);
    ChuyenNganhEntity findByMaCN(String maCN);
}