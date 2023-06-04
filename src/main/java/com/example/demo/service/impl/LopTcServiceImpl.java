package com.example.demo.service.impl;

import com.example.demo.entity.LopTcEntity;
import com.example.demo.repository.LopTcRepository;
import com.example.demo.service.LopTcService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LopTcServiceImpl implements LopTcService {

    @Autowired
    private LopTcRepository dsLopTcRepository;

    @Override
    public List<LopTcEntity> getListLopTcByMaLop(String maLop, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<LopTcEntity> resultPage = dsLopTcRepository.findAllByMaLop(maLop, pageable);
        return resultPage.getContent();
    }

    @Override
    public List<LopTcEntity> getListLopTcByMaMH(String maMH) {
        return dsLopTcRepository.findAllByMaMH(maMH);
    }

    @Override
    public List<LopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach) {
        return dsLopTcRepository.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
    }

    @Override
    public List<LopTcEntity> findAllByMaMHAndMaKeHoach(String maMH, String maKeHoach) {
        return dsLopTcRepository.findAllByMaMHAndMaKeHoach(maMH, maKeHoach);
    }

    @Override
    public LopTcEntity getLopTcByMaLopTcAndMaKeHoach(String maLopTc, String maKeHoach) {
        return dsLopTcRepository.getLopTcByMaLopTcAndMaKeHoach(maLopTc, maKeHoach);
    }

//    @Override
//    public List<LopTcEntity> findAllByMaGVAndMaKeHoach(String maGV, String maKeHoach) {
//        return dsLopTcRepository.findAllByMaGVAndMaKeHoach(maGV, maKeHoach);
//    }

    @Override
    public LopTcEntity getLopTcByMaLopTc(String maLopTc) {
        return dsLopTcRepository.getLopTcByMaLopTc(maLopTc);
    }

    @Override
    public List<LopTcEntity> findAllByMaKeHoach(String maKeHoach) {
        return dsLopTcRepository.findAllByMaKeHoach(maKeHoach);
    }

    @Override
    public LopTcEntity getLopTcById(String id) {
        return dsLopTcRepository.getLopTcById(id);
    }

}
