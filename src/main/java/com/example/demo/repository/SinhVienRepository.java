package com.example.demo.repository;

import com.example.demo.entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SinhVienRepository extends MongoRepository<SinhVienEntity, String> {

    int countSinhVienByMaSV(String maSV);
    int countSinhVienByEmail(String email);
    int countSinhVienById(String id);

    @Query(value = "{'maSV': ?0, '_id': {$ne: ?1}}", count = true)
    Long countSinhVienByMaSVAndNotId(String maSV, String id);

    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countSinhVienByEmailAndNotId(String email, String id);

    int countSinhVienByMaLop(String maLop);

    List<SinhVienEntity> findAllByMaLop(String maLop);
    Page<SinhVienEntity> findAllByMaLop(String maLop, Pageable pageable);

    SinhVienEntity findByMaSV(String maSV);
}
