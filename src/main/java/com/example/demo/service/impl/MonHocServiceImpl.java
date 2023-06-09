package com.example.demo.service.impl;

import com.example.demo.dto.MonHocModifyDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.MonHocService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
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

    @Autowired
    private MHTQRepository mhtqRepository;

    @Autowired
    private CTDTRepository ctdtRepository;

    @Autowired
    private TichLuyRepository tichLuyRepository;

    @Autowired
    private LopRepository lopRepository;

//    public List<MonHocEntity> findAllByMaLop(String maLop, int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<MonHocEntity> resultPage = monHocRepository.findAllByMaLop(maLop, pageable);
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

    @Override
    public List<MonHocEntity> findAllByMaLop(String maLop){

        /* LOP -> CTDT -> TICH_LUY -> MON_HOC list */
        LopEntity lopEntity = lopRepository.findByMaLop(maLop);
//        CTDTEntity ctdtEntity = ctdtRepository.findByMaCTDT(lopEntity.getMaCTDT());
        List<TichLuyEntity> tichLuyEntityList = tichLuyRepository.findAllByMaCTDT(lopEntity.getMaCTDT());
        List<MonHocEntity> monHocEntityList = new ArrayList<>();
        for(TichLuyEntity tichLuyEntity : tichLuyEntityList){
            MonHocEntity monHocEntity = monHocRepository.findByMaMH(tichLuyEntity.getMaMH());
            if(monHocEntity != null){
                monHocEntityList.add(monHocEntity);
            }
        }

        return monHocEntityList;
    }

    @Override
    public List<MonHocModifyDto> findAllMonHocModify() {
        List<MonHocModifyDto> monHocModifyDtoList = new ArrayList<>();
        List<MonHocEntity> monHocEntityList = monHocRepository.findAll();
        for(MonHocEntity monHocEntity : monHocEntityList){
            List<MHTQEntity> mhtqEntityList = mhtqRepository.findAllByMaMH(monHocEntity.getMaMH());
            List<String> maMHTQList = new ArrayList<>();
            List<String> tenMHTQList = new ArrayList<>();
            for(MHTQEntity mhtqEntity : mhtqEntityList){
                maMHTQList.add(mhtqEntity.getMaMHTQ());
                tenMHTQList.add(mhtqEntity.getTenMHTQ());
            }

            MonHocModifyDto monHocModifyDto = new MonHocModifyDto();
            monHocModifyDto.setId(monHocEntity.getId());
            monHocModifyDto.setMaMH(monHocEntity.getMaMH());
            monHocModifyDto.setTenMH(monHocEntity.getTenMH());
            monHocModifyDto.setMaMHTQList(maMHTQList);
            monHocModifyDto.setTenMHTQList(tenMHTQList);
            monHocModifyDtoList.add(monHocModifyDto);
        }
        return monHocModifyDtoList;
    }

}
