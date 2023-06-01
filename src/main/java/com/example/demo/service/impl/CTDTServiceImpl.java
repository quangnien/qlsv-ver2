package com.example.demo.service.impl;

import com.example.demo.entity.CTDTEntity;
import com.example.demo.repository.CTDTRepository;
import com.example.demo.service.CTDTService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CTDTServiceImpl implements CTDTService {

    @Autowired
    private CTDTRepository ctdtRepository;

    @Override
    public CTDTEntity addCTDT(CTDTEntity CTDTEntity) {
        CTDTEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return ctdtRepository.save(CTDTEntity);
    }

    @Override
    public List<CTDTEntity> findAllCTDT() {
        return ctdtRepository.findAll();
    }

    @Override
    public CTDTEntity findById(String id) {
        return ctdtRepository.findById(id).get();
    }

    @Override
    public CTDTEntity findByMaCTDT(String maCTDT) {
        return ctdtRepository.findByMaCTDT(maCTDT);
    }

}
