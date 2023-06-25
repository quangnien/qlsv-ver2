package com.example.demo.service.impl;

import com.example.demo.dto.MoLopTcDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CTDTService;
import com.example.demo.service.KeHoachNamService;
import com.example.demo.service.LopTcService;
import com.example.demo.service.TichLuyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LopTcServiceImpl implements LopTcService {

    @Autowired
    private LopTcRepository lopTcRepository;

    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private TichLuyService tichLuyService;

    @Autowired
    private CTDTService ctdtService;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private CTDTRepository ctdtRepositoryt;

    @Autowired
    private TichLuyRepository tichLuyRepository;

    @Override
    public List<LopTcEntity> findAllByMaLop(String maLop){
        List<LopTcEntity> resultPage = lopTcRepository.findAllByMaLop(maLop);
        for(LopTcEntity lopTcEntity : resultPage){
            LopEntity lopEntity = lopRepository.findByMaLop(lopTcEntity.getMaLop());
            CTDTEntity ctdtEntity = ctdtRepositoryt.findByMaCTDT(lopEntity.getMaCTDT());
//            MonHocEntity monHocEntity = monHocRepository.findByMaMH(lopTcEntity.getMaMH());

            TichLuyEntity tichLuyEntity = tichLuyRepository.findByMaCTDTAndMaMH(ctdtEntity.getMaCTDT(), lopTcEntity.getMaMH());
            lopTcEntity.setStc(tichLuyEntity.getStc());
        }
        return resultPage;
    }

    @Override
    public List<LopTcEntity> getListLopTcByMaMH(String maMH) {
        return lopTcRepository.findAllByMaMH(maMH);
    }

    @Override
    public List<LopTcEntity> findAllByMaLopAndMaKeHoach(String maLop, String maKeHoach) {
        return lopTcRepository.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
    }

    @Override
    public List<LopTcEntity> findAllByMaMHAndMaKeHoach(String maMH, String maKeHoach) {
        return lopTcRepository.findAllByMaMHAndMaKeHoach(maMH, maKeHoach);
    }

    @Override
    public LopTcEntity getLopTcByMaLopTcAndMaKeHoach(String maLopTc, String maKeHoach) {
        return lopTcRepository.getLopTcByMaLopTcAndMaKeHoach(maLopTc, maKeHoach);
    }

//    @Override
//    public List<LopTcEntity> findAllByMaGVAndMaKeHoach(String maGV, String maKeHoach) {
//        return dsLopTcRepository.findAllByMaGVAndMaKeHoach(maGV, maKeHoach);
//    }

    @Override
    public LopTcEntity getLopTcByMaLopTc(String maLopTc) {
        return lopTcRepository.findByMaLopTc(maLopTc);
    }

    @Override
    public List<LopTcEntity> findAllByMaKeHoach(String maKeHoach) {
        return lopTcRepository.findAllByMaKeHoach(maKeHoach);
    }

    @Override
    public LopTcEntity findById(String id) {
        return lopTcRepository.findById(id).get();
    }

    @Override
    public void createListNew(MoLopTcDto moLopTcDto) {

        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
        String maKeHoachClosest = keHoachNamEntity.getMaKeHoach();

        for(String maMonHoc : moLopTcDto.getMaMonHocList()){
            LopTcEntity lopTcEntity = new LopTcEntity();

            lopTcEntity.setMaKeHoach(maKeHoachClosest);
            lopTcEntity.setMaMH(maMonHoc);
            lopTcEntity.setMaLop(moLopTcDto.getMaLop());
            lopTcEntity.setMaLopTc(maMonHoc + moLopTcDto.getMaLop());

            lopTcEntity.setSoLuong(100);
            lopTcEntity.setSoLuongCon(100);
            lopTcEntity.setTrangThai(1);
            lopTcEntity.setTimeBd(keHoachNamEntity.getTimeStudyBegin());
            lopTcEntity.setTimeKt(keHoachNamEntity.getTimeStudyEnd());

            /* begin VẾT tìm nienKhoa */
            String nienKhoa = "";
            TichLuyEntity tichLuyEntity = tichLuyService.findAllByMaMH(maMonHoc) != null ? tichLuyService.findAllByMaMH(maMonHoc).get(0) : null;
            if(tichLuyEntity != null){
                CTDTEntity ctdtEntity = ctdtService.findByMaCTDT(tichLuyEntity.getMaCTDT());
                if(ctdtEntity != null){
                    nienKhoa = ctdtEntity.getNienKhoa();
                }
            }

            lopTcEntity.setNienKhoa(nienKhoa);
            /* end VẾT tìm nienKhoa */

            MonHocEntity monHocEntity = monHocRepository.findByMaMH(maMonHoc);
            lopTcEntity.setTenMH(monHocEntity.getTenMH());

            LopEntity lopEntity = lopRepository.findByMaLop(moLopTcDto.getMaLop());
            lopTcEntity.setTenLop(lopEntity.getTenLop());

            lopTcRepository.save(lopTcEntity);
        }
    }

}
