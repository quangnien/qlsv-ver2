package com.example.demo.repository;

import com.example.demo.entity.GiangVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GiangVienRepository extends MongoRepository<GiangVienEntity, String> {

    int countGiangVienById(String id);
    int countGiangVienByMaGV(String maGV);
    int countGiangVienByEmail(String email);
//    int countGiangVienByMaKhoa(String maKhoa);

    @Query(value = "{'maGV': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByMaGVAndNotId(String maGV, String id);

    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByEmailAndNotId(String email, String id);

    GiangVienEntity findByMaGV(String maGV);
//    List<GiangVienEntity> findByByMaKhoa(String maKhoa);
//    Page<GiangVienEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

}