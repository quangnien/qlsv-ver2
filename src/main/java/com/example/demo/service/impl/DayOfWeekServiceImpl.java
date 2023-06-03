package com.example.demo.service.impl;

import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.repository.DayOfWeekRepository;
import com.example.demo.service.DayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DayOfWeekServiceImpl implements DayOfWeekService {

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @Override
    public DayOfWeekEntity addNew(DayOfWeekEntity dayOfWeekEntity) {
        dayOfWeekEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return dayOfWeekRepository.save(dayOfWeekEntity);
    }

    @Override
    public List<DayOfWeekEntity> findAll() {
        return dayOfWeekRepository.findAll();
    }

    @Override
    public DayOfWeekEntity findById(String id) {
        return dayOfWeekRepository.findById(id).get();
    }

    @Override
    public DayOfWeekEntity findByMaDOW(String maDOW) {
        return dayOfWeekRepository.findByMaDOW(maDOW);
    }

}
