package com.example.demo.repository;

import com.example.demo.entity.MHTQEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MHTQRepository extends MongoRepository<MHTQEntity, String> {
//    int countMHTQByMaMHTQ(String maMHTQ);
    int countMHTQById(String id);

//    @Query(value = "{'maMH': ?0, '_id': {$ne: ?1}}", count = true)
//    Long countMHTQByMaMHTQAndNotId(String maMH, String id);

    @Query(value = "{ 'maMH': ?0, 'maMHTQ': ?1 }", count = true)
    Long countMHTQByMaMHAndMaMHTQ(String maMH, String maMHTQ);

    List<MHTQEntity> findAllByMaMH(String maMH);
    List<MHTQEntity> findAllByMaMHTQ(String maMHTQ);

//    Page<MHTQEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

//    List<MHTQEntity> findByTenMHLikeIgnoreCase(String keySearch);

}
