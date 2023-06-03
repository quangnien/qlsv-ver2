package com.example.demo.service;

import com.example.demo.entity.KeHoachNamEntity;
import com.example.demo.entity.KeHoachNamEntity;

import java.util.List;

public interface KeHoachNamService {

    public KeHoachNamEntity addNew(KeHoachNamEntity keHoachNamEntity);
    public List<KeHoachNamEntity> findAll();
    public KeHoachNamEntity findById(String id);
    public KeHoachNamEntity findByMaKeHoach(String maKeHoachNam);

    public KeHoachNamEntity getKeHoachNamClosest();

}
