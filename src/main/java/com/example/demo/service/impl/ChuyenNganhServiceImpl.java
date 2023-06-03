package com.example.demo.service.impl;

import com.example.demo.entity.ChuyenNganhEntity;
import com.example.demo.repository.ChuyenNganhRepository;
import com.example.demo.service.ChuyenNganhService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChuyenNganhServiceImpl implements ChuyenNganhService {

    @Autowired
    private ChuyenNganhRepository chuyenNganhRepository;

    @Override
    public ChuyenNganhEntity addNew(ChuyenNganhEntity chuyenNganhEntity) {
        chuyenNganhEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return chuyenNganhRepository.save(chuyenNganhEntity);
    }

    @Override
    public List<ChuyenNganhEntity> findAll() {
        return chuyenNganhRepository.findAll();
    }

    @Override
    public ChuyenNganhEntity findById(String id) {
        return chuyenNganhRepository.findById(id).get();
    }

    @Override
    public ChuyenNganhEntity findByMaCN(String maCN) {
        return chuyenNganhRepository.findByMaCN(maCN);
    }

}
