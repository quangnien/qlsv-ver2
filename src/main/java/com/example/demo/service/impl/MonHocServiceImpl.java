package com.example.demo.service.impl;

import com.example.demo.entity.MonHocEntity;
import com.example.demo.repository.MonHocRepository;
import com.example.demo.service.MonHocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MonHocServiceImpl implements MonHocService {

    @Autowired
    private MonHocRepository monHocRepository;

//    public List<MonHocEntity> findAllByMaKhoa(String maKhoa, int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<MonHocEntity> resultPage = monHocRepository.findAllByMaKhoa(maKhoa, pageable);
//        return resultPage.getContent();
//    }

//    public MonHocEntity getByMaMH(String maMh) {
//        return monHocRepository.getByMaMH(maMh);
//    }

    @Override
    public List<MonHocEntity> findByTenMHContainingIgnoreCaseLike(String keySearch) {
        return monHocRepository.findByTenMHLikeIgnoreCase(keySearch);
    }

    @Override
    public MonHocEntity addNew(MonHocEntity monHocEntity) {
        monHocEntity.setId(UUID.randomUUID().toString().split("-")[0]);
        return monHocRepository.save(monHocEntity);
    }

    @Override
    public MonHocEntity updateExist(MonHocEntity monHocEntity) {
        return monHocRepository.save(monHocEntity);
    }

    public List<MonHocEntity> findAll() {
        return monHocRepository.findAll();
    }

    @Override
    public MonHocEntity findById(String id) {
        return monHocRepository.findById(id).get();
    }

    @Override
    public MonHocEntity findByMaMH(String maMH) {
        return monHocRepository.findByMaMH(maMH);
    }

    @Override
    public List<String> deleteList(List<String> lstId) {

        List<String> lstSuccess = new ArrayList<>();

        for (String itemId : lstId) {
            int countMaMonHoc = monHocRepository.countMonHocById(itemId);
            if(countMaMonHoc > 0){

                /* important: validator */
//                MonHocEntity monHocEntity = monHocRepository.findById(itemId).get();
//                int countGiangVienOnDsLopTcTable = dsLopTcRepository.countDsLopTcByMaMh(monHocEntity.getMaMh());
//
//                if(countGiangVienOnDsLopTcTable > 0){
//                    continue;
//                }
//                else {
//                    lstSuccess.add(itemId);
//                    monHocRepository.deleteById(itemId);
//                }

            }
        }
        return null;
    }

}
