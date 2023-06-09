package com.example.demo.service.impl;

import com.example.demo.entity.ERole;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.LopEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LopService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepository;

    @Override
    public LopEntity addNew(LopEntity lopEntity) {
        lopEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return lopRepository.save(lopEntity);
    }

    @Override
    public List<LopEntity> findAll() {
        return lopRepository.findAll();
    }

    @Override
    public LopEntity findById(String id) {
        return lopRepository.findById(id).get();
    }

    @Override
    public LopEntity findByMaLop(String maLop) {
        return lopRepository.findByMaLop(maLop);
    }

}
