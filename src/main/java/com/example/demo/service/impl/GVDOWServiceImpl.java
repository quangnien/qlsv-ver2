package com.example.demo.service.impl;

import com.example.demo.dto.GVDOWDto;
import com.example.demo.entity.GvDowEntity;
import com.example.demo.service.GVDOWService;
import com.example.demo.repository.GVDOWRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GVDOWServiceImpl implements GVDOWService {

    @Autowired
    private GVDOWRepository gvdowRepository;

    @Override
    public GVDOWDto updateExist(GVDOWDto gvdowDto) {
        String maGV = gvdowDto.getMaGV();

        /* REMOVE ALL -> maGV */
        List<GvDowEntity> gvDowEntityList = gvdowRepository.findAllByMaGV(gvdowDto.getMaGV());
        if(gvDowEntityList != null){
            for(GvDowEntity item : gvDowEntityList){
                gvdowRepository.deleteById(item.getId());
            }
        }
        /* END REMOVE ALL -> maGV */

        for(String maDOW: gvdowDto.getMaDOWList()){
            GvDowEntity gvDowEntity = new GvDowEntity();
            gvDowEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            gvDowEntity.setMaDOW(maDOW);
            gvDowEntity.setMaGV(maGV);
            gvdowRepository.save(gvDowEntity);
        }

        return gvdowDto;
    }

    @Override
    public List<GvDowEntity> findAllByMaGV(String maGV) {
        return gvdowRepository.findAllByMaGV(maGV);
    }

    @Override
    public List<GvDowEntity> findAllByMaDOW(String maDOW) {
        return gvdowRepository.findAllByMaDOW(maDOW);
    }

    @Override
    public GvDowEntity findById(String id) {
        return gvdowRepository.findById(id).get();
    }

    @Override
    public void deleteRecord(String id) {
        gvdowRepository.deleteById(id);
    }
}
