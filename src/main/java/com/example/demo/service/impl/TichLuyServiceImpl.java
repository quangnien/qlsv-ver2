package com.example.demo.service.impl;

import com.example.demo.entity.TichLuyEntity;
import com.example.demo.repository.TichLuyRepository;
import com.example.demo.service.TichLuyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TichLuyServiceImpl implements TichLuyService {

    @Autowired
    private TichLuyRepository tichLuyRepository;

    @Override
    public void updateExist(List<TichLuyEntity> tichLuyEntityList, String tenCTDT) {

        /* begin REMOVE ALL RECORD */
        String maCTDT = tichLuyEntityList.get(0).getMaCTDT();
        List<TichLuyEntity> tichLuyEntityToRemove = tichLuyRepository.findAllByMaCTDT(maCTDT);
        for(TichLuyEntity itemToRemove : tichLuyEntityToRemove){
            tichLuyRepository.deleteById(itemToRemove.getId());
        }
        /* end REMOVE ALL RECORD */

        for(TichLuyEntity tichLuyEntity: tichLuyEntityList){
            tichLuyEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            tichLuyEntity.setTenCTDT(tenCTDT);
            tichLuyRepository.save(tichLuyEntity);
        }
    }

    @Override
    public List<TichLuyEntity> findAllByMaCTDT(String maCTDT) {
        return tichLuyRepository.findAllByMaCTDT(maCTDT);
    }

    @Override
    public List<TichLuyEntity> findAllByMaMH(String maMH) {
        return tichLuyRepository.findAllByMaMH(maMH);
    }

    @Override
    public TichLuyEntity findById(String id) {
        return tichLuyRepository.findById(id).get();
    }

}