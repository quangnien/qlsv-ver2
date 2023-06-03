package com.example.demo.service.impl;

import com.example.demo.dto.MHMHTQDto;
import com.example.demo.entity.MHTQEntity;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.repository.MHTQRepository;
import com.example.demo.repository.MonHocRepository;
import com.example.demo.service.MHTQService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MHTQServiceImpl implements MHTQService {

    @Autowired
    private MHTQRepository mhtqRepository;

    @Autowired
    private MonHocRepository monHocRepository;


    @Override
    public MHMHTQDto updateExist(MHMHTQDto mhmhtqDto) {
        String maMH = mhmhtqDto.getMaMH();

        for(String maMHTQ: mhmhtqDto.getMaMHTQList()){
            MHTQEntity mhtqEntity = new MHTQEntity();
            mhtqEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            mhtqEntity.setMaMHTQ(maMHTQ);
            mhtqEntity.setMaMH(maMH);

            MonHocEntity monHocEntity = monHocRepository.findByMaMH(maMH);
            MonHocEntity monHocTienQuyetEntity = monHocRepository.findByMaMH(maMHTQ);

            mhtqEntity.setTenMH(monHocEntity.getTenMH());
            mhtqEntity.setTenMHTQ(monHocTienQuyetEntity.getTenMH());

            mhtqRepository.save(mhtqEntity);
        }

        return mhmhtqDto;
    }

    @Override
    public List<MHTQEntity> findAllByMaMH(String maMH) {
        return mhtqRepository.findAllByMaMH(maMH);
    }

    @Override
    public MHTQEntity findById(String id) {
        return mhtqRepository.findById(id).get();
    }

    @Override
    public List<MHTQEntity> findAll() {
        return mhtqRepository.findAll();
    }

}
