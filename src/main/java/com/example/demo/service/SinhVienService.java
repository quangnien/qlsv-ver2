package com.example.demo.service;

import com.example.demo.entity.SinhVienEntity;

import java.util.List;

public interface SinhVienService {

    public SinhVienEntity addSinhVien(SinhVienEntity sinhVienEntity);
    public SinhVienEntity updateSinhVien(SinhVienEntity sinhVienEntity);
    public List<String> deleteListSinhVien(List<String> listId);
    public List<SinhVienEntity> findAll();
    public SinhVienEntity findById(String id);
    public SinhVienEntity findByMaSV(String maSV);
    public List<SinhVienEntity> findAllByMaLop(String maLop, int page, int size);
}
