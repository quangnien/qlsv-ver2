package com.example.demo.repository;

import com.example.demo.entity.MonHocEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MonHocRepository extends MongoRepository<MonHocEntity, String> {
    int countMonHocByMaMH(String maMH);
    int countMonHocById(String id);

    @Query(value = "{'maMH': ?0, '_id': {$ne: ?1}}", count = true)
    Long countMonHocByMaMHAndNotId(String maMH, String id);

    MonHocEntity findByMaMH(String maMH);

//    Page<MonHocEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

    List<MonHocEntity> findByTenMHLikeIgnoreCase(String keySearch);

}
