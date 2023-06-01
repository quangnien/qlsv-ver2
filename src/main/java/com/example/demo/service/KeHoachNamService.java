package com.example.demo.service;

import com.example.demo.entity.KeHoachNamEntity;

public interface KeHoachNamService {

    public KeHoachNamEntity getKeHoachNamByMaKeHoach(String maKeHoachNam);
    public KeHoachNamEntity getKeHoachNamClosest();

}
