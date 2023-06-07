package com.example.demo.service;

import com.example.demo.dto.DangKyDto;
import com.example.demo.entity.DangKyEntity;

import java.util.List;

public interface DangKyService {
    public DangKyDto updateExistDangKyMon(DangKyDto dangKyDto);
    public void updateExistList(List<DangKyEntity> dangKyEntityList);
    public List<DangKyEntity> findAllByMaSV(String maSV);
    public List<DangKyEntity> findAllByMaSVAndMaKeHoach(String maSV, String maKeHoach);
    public List<DangKyEntity> findAllByMaLopTc(String maLopTc);
    public DangKyEntity findById(String id);

    public List<DangKyEntity> findAllByMaLopTc(String maLopTc, int page, int size);

}