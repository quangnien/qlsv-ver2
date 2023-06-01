package com.example.demo.repository;

import com.example.demo.entity.KeHoachNamEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface KeHoachNamRepository extends MongoRepository<KeHoachNamEntity, String> {

    int countKeHoachNamByMaKeHoach(String maKeHoach);
    int countKeHoachNamById(String id);

    @Query(value = "{'maKeHoachNam': ?0, '_id': {$ne: ?1}}", count = true)
    Long countKeHoachNamByMaKeHoachNamAndNotId(String maKeHoachNam, String id);

    KeHoachNamEntity getKeHoachNamByMaKeHoach(String maKeHoach);

}
