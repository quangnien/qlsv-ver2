package com.example.demo.service;

import com.example.demo.entity.user.GiangVienEntity;

import java.util.List;

public interface GiangVienService {

    public GiangVienEntity addNew(GiangVienEntity giangVienEntity);
    public GiangVienEntity updateGiangVien(GiangVienEntity giangVienEntity);
    public List<String> deleteListGiangVien(List<String> listId);
    public List<GiangVienEntity> findAll();
    public GiangVienEntity findById(String id);
    public GiangVienEntity findByMaGV(String maGV);

    // paging
    List<GiangVienEntity> findAll(int page, int size);

    //    public List<TkbDto> getListTKBForGV(String maGiangVien, String maKeHoach, int tuan);
//    public List<WrapTkbDto> getListTKBForGV(String maGiangVien, String maKeHoach, int tuan);

//    List<GiangVienEntity> findByMaKhoa(int page, int size, String maKhoa);
}
