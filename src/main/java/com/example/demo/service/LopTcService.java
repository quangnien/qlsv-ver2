package com.example.demo.service;

import com.example.demo.dto.MoLopTcDto;
import com.example.demo.entity.LopTcEntity;

import java.util.List;

public interface LopTcService {

    public List<LopTcEntity> findAllByMaLop(String maLop, int page, int size);

    public List<LopTcEntity> getListLopTcByMaMH(String maMH);

    public List<LopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach);

    public List<LopTcEntity> findAllByMaMHAndMaKeHoach(String maMH, String maKeHoach);

    public LopTcEntity getLopTcByMaLopTcAndMaKeHoach(String maLopTc, String maKeHoach);

//    public List<LopTcEntity> findAllByMaGVAndMaKeHoach(String maGV, String maKeHoach);

    public LopTcEntity getLopTcByMaLopTc(String maLopTc);

    public List<LopTcEntity> findAllByMaKeHoach(String maKeHoach);

    public LopTcEntity findById(String id);

    void createListNew(MoLopTcDto moLopTcDto);
}
