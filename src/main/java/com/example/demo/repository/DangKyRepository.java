package com.example.demo.repository;

import com.example.demo.entity.DangKyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DangKyRepository extends MongoRepository<DangKyEntity, String> {

    @Query(value = "{maSV: ?0, maLopTc: ?1 }", count = true)
    Long countByMaSVAndMaLopTc(String maSV, String maLopTc);

    int countDangKyById(String id);

    List<DangKyEntity> findAllByMaSV(String maSV);
    List<DangKyEntity> findAllByMaLopTc(String maLopTc);
    List<DangKyEntity> findAllByMaSVAndMaKeHoach(String maSV, String maKeHoach);

    Page<DangKyEntity> findAllByMaLopTc(String maLopTc, Pageable pageable);
    DangKyEntity findByMaSVAndMaLopTc(String maSV, String maLopTc);

}