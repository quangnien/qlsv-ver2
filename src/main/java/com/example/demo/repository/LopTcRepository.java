package com.example.demo.repository;

import com.example.demo.entity.LopTcEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LopTcRepository extends MongoRepository<LopTcEntity, String> {

    int countLopTcByMaLopTc(String maLopTc);
    int countLopTcById(String id);

    @Query(value = "{'maLopTc': ?0, '_id': {$ne: ?1}}", count = true)
    Long countLopTcByMaLopTcAndNotId(String maLopTc, String id);

    List<LopTcEntity> getListDsLopByMaLop(String maLop);

    LopTcEntity findByMaLopTc(String maLopTc);

    int countLopTcByMaLop(String maLop);
//    int countLopTcByMaGV(String maGv);
    int countLopTcByMaMH(String maMh);

    Page<LopTcEntity> findAllByMaLop(String maLop, Pageable pageable);

    List<LopTcEntity> findAllByMaMH(String maMh);

    List<LopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach);

    List<LopTcEntity> findAllByMaMHAndMaKeHoach(String maMh, String maKeHoach);

    LopTcEntity getLopTcByMaLopTcAndMaKeHoach(String maLopTc, String maKeHoach);
//    List<LopTcEntity> findAllByMaGVAndMaKeHoach(String maGv, String maKeHoach);

    List<LopTcEntity> findAllByMaKeHoach(String maKeHoach);

}
