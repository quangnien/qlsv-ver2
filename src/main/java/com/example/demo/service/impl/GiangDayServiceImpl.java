package com.example.demo.service.impl;

import com.example.demo.dto.GiangDayDto;
import com.example.demo.entity.GiangDayEntity;
import com.example.demo.entity.user.GiangVienEntity;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.repository.GiangDayRepository;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.MonHocRepository;
import com.example.demo.service.GiangDayService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GiangDayServiceImpl implements GiangDayService {

    @Autowired
    private GiangDayRepository giangDayRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Override
    public GiangDayDto updateExist(GiangDayDto giangDayDto) {

        String maGV = giangDayDto.getMaGV();

        /* REMOVE ALL -> maGV */
        List<GiangDayEntity> giangDayEntityList = giangDayRepository.findAllByMaGV(giangDayDto.getMaGV());
        if(giangDayEntityList != null){
            for(GiangDayEntity item : giangDayEntityList){
                giangDayRepository.deleteById(item.getId());
            }
        }
        /* END REMOVE ALL -> maGV */

        for(String maMH: giangDayDto.getMaMHList()){
            GiangDayEntity giangDayEntity = new GiangDayEntity();
            giangDayEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            giangDayEntity.setMaMH(maMH);
            giangDayEntity.setMaGV(maGV);

            GiangVienEntity giangVienEntity = giangVienRepository.findByMaGV(maGV);
            MonHocEntity monHocEntity = monHocRepository.findByMaMH(maMH);

            giangDayEntity.setTenGV(giangVienEntity.getHo() + " " + giangVienEntity.getTen());
            giangDayEntity.setTenMH(monHocEntity.getTenMH());

            giangDayRepository.save(giangDayEntity);
        }

        return giangDayDto;
    }

    @Override
    public List<GiangDayEntity> findAllByMaGV(String maGV) {
        return giangDayRepository.findAllByMaGV(maGV);
    }

    @Override
    public List<GiangDayEntity> findAllByMaMH(String maMH) {
        return giangDayRepository.findAllByMaMH(maMH);
    }

    @Override
    public GiangDayEntity findById(String id) {
        return giangDayRepository.findById(id).get();
    }

}
