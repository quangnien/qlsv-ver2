package com.example.demo.service.impl;

import com.example.demo.dto.DangKyDto;
import com.example.demo.entity.*;
import com.example.demo.entity.user.SinhVienEntity;
import com.example.demo.enumdef.XepLoaiEnum;
import com.example.demo.repository.*;
import com.example.demo.service.DangKyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DangKyServiceImpl implements DangKyService {

    @Autowired
    private DangKyRepository dangKyRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private LopTcRepository lopTcRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private TichLuyRepository tichLuyRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    public DangKyDto updateExistDangKyMon(DangKyDto dangKyDto) {

        String maSV = dangKyDto.getMaSV();

        /* REMOVE ALL -> maSV */
        List<DangKyEntity> dangKyEntityList = dangKyRepository.findAllByMaSV(dangKyDto.getMaSV());
        if(dangKyEntityList != null){
            for(DangKyEntity item : dangKyEntityList){

                /*SET SOLUONGCON CUA LOPTINCHI*/
                LopTcEntity lopTcEntity = lopTcRepository.findByMaLopTc(item.getMaLopTc());
                if(lopTcEntity != null){
                    lopTcEntity.setSoLuongCon(lopTcEntity.getSoLuongCon() + 1);
                }
                /*END SET SOLUONGCON CUA LOPTINCHI*/

                dangKyRepository.deleteById(item.getId());
            }
        }
        /* END REMOVE ALL -> maSV */

        for(String maLopTc: dangKyDto.getMaLopTcList()){

            DangKyEntity dangKyEntity = new DangKyEntity();
            dangKyEntity.setId(UUID.randomUUID().toString().split("-")[0]);
            dangKyEntity.setMaLopTc(maLopTc);
            dangKyEntity.setMaSV(maSV);
            dangKyEntity.setCc(0);
            dangKyEntity.setGk(0);
            dangKyEntity.setCk(0);
            dangKyEntity.setTb(0);
            dangKyEntity.setXepLoai(XepLoaiEnum.F.getName());

            /* begin SET SOLUONGCON CUA LOPTINCHI */
            LopTcEntity lopTcEntity = lopTcRepository.findByMaLopTc(maLopTc);
            if(lopTcEntity != null){
                lopTcEntity.setSoLuongCon(lopTcEntity.getSoLuongCon() - 1);
            }
            /* END SET SOLUONGCON CUA LOPTINCHI */

            /* end SET MORE INFO */
            SinhVienEntity sinhVientity = sinhVienRepository.findByMaSV(maSV);

            dangKyEntity.setTenSV(sinhVientity.getHo() + " " + sinhVientity.getTen());
            dangKyEntity.setTenMH(lopTcEntity.getTenMH());

            dangKyEntity.setMaKeHoach(lopTcEntity.getMaKeHoach());

            // ---
            LopEntity lopEntity = lopRepository.findByMaLop(lopTcEntity.getMaLop());
            TichLuyEntity tichLuyEntity = tichLuyRepository.findByMaCTDTAndMaMH(lopEntity.getMaCTDT(), lopTcEntity.getMaMH());

            dangKyEntity.setTlCc(tichLuyEntity.getTlCc());
            dangKyEntity.setTlGk(tichLuyEntity.getTlGk());
            dangKyEntity.setTlCk(tichLuyEntity.getTlCk());
            dangKyEntity.setStc(tichLuyEntity.getStc());

            /* end SET MORE INFO */



            dangKyRepository.save(dangKyEntity);
        }

        return dangKyDto;
    }

    @Override
    public void updateExistList(List<DangKyEntity> dangKyEntityList) {

        for(DangKyEntity dangKyEntity : dangKyEntityList){

            /* set id for dangKyEntity */
            DangKyEntity dangKyEntityFromDB = dangKyRepository.findByMaSVAndMaLopTc(dangKyEntity.getMaSV(), dangKyEntity.getMaLopTc());
            dangKyEntity.setId(dangKyEntityFromDB.getId());

            /* Vì TB là 1 field được sinh tự động khi edit */
            float tb = (float) (dangKyEntity.getCc()*dangKyEntity.getTlCc()
                    + dangKyEntity.getGk()*dangKyEntity.getTlGk()
                    + dangKyEntity.getCk()*dangKyEntity.getTlCk()) / 100;

            float tbRound = Math.round(tb * 10.0f) / 10.0f;

            dangKyEntity.setTb(tbRound);
            /* ______________________________________ */

            if(tb >= 9 ){
                dangKyEntity.setXepLoai(XepLoaiEnum.A_PLUS.getName());
            }
            else if(tb >= 8.5){
                dangKyEntity.setXepLoai(XepLoaiEnum.A.getName());
            }
            else if(tb >= 8){
                dangKyEntity.setXepLoai(XepLoaiEnum.B_PLUS.getName());
            }
            else if(tb >= 7){
                dangKyEntity.setXepLoai(XepLoaiEnum.B.getName());
            }
            else if(tb >= 6.5){
                dangKyEntity.setXepLoai(XepLoaiEnum.C_PLUS.getName());
            }
            else if(tb >= 5.5){
                dangKyEntity.setXepLoai(XepLoaiEnum.C.getName());
            }
            else if(tb >= 5){
                dangKyEntity.setXepLoai(XepLoaiEnum.D_PLUS.getName());
            }
            else if(tb >= 4){
                dangKyEntity.setXepLoai(XepLoaiEnum.D.getName());
            }
            else {
                dangKyEntity.setXepLoai(XepLoaiEnum.F.getName());
            }

            dangKyRepository.save(dangKyEntity);
        }
    }

    @Override
    public List<DangKyEntity> findAllByMaSV(String maSV) {
        return dangKyRepository.findAllByMaSV(maSV);
    }

    @Override
    public List<DangKyEntity> findAllByMaSVAndMaKeHoach(String maSV, String maKeHoach) {
        return dangKyRepository.findAllByMaSVAndMaKeHoach(maSV, maKeHoach);
    }

    @Override
    public List<DangKyEntity> findAllByMaLopTc(String maLopTc) {
        return dangKyRepository.findAllByMaLopTc(maLopTc);
    }

    @Override
    public DangKyEntity findById(String id) {
        return dangKyRepository.findById(id).get();
    }

    @Override
    public List<DangKyEntity> findAllByMaLopTc(String maLopTc, int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<DangKyEntity> resultPage = dangKyRepository.findAllByMaLopTc(maLopTc, pageable);

        return resultPage.getContent();
    }
}
