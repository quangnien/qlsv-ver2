package com.example.demo.validation;

import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.dto.DangKyDto;
import com.example.demo.entity.DangKyEntity;
import com.example.demo.entity.KeHoachNamEntity;
import com.example.demo.entity.LopTcEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.*;
import com.example.demo.service.LopTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorDangKy implements Validator {

    @Autowired
    private DangKyRepository dangKyRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;
    @Autowired
    private LopTcRepository lopTcRepository;
    @Autowired
    private KeHoachNamRepository keHoachNamRepository;
    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private LopTcService lopTcService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateGetDangKyById(String dangKyId) throws BusinessException {

        int countDangKy = dangKyRepository.countDangKyById(dangKyId);

        if (countDangKy == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DANGKY_NOT_FOUND_DSLOPTC);
        }
    }

    @Transactional
    public void validateGetListDangKyByMaLopTc(String maLopTc) throws BusinessException {

        if(maLopTc == null || "".equals(maLopTc)){
            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
        }
        else {
            int countLopTcByMaLopTc = lopTcRepository.countLopTcByMaLopTc(maLopTc);

            if (countLopTcByMaLopTc == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
            }
        }

    }

    @Transactional
    public void validateThongKeDangKy(String id, String col) throws BusinessException {

            if(id != null && !id.equals("")){

                int countMaDsLopTc = lopTcRepository.countLopTcById(id);

                if (countMaDsLopTc == 0) {
                    throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
                }
            }
    }

    @Transactional
    public void validateGetListDangKyByMaSVAndMaKeHoach(String maSV, String maKeHoach) throws BusinessException {

        if(maSV == null || "".equals(maSV)){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if(maKeHoach == null || "".equals(maKeHoach)){
            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
        }
        else {
            int countSVByMaSV = sinhVienRepository.countSinhVienByMaSV(maSV);
            int countByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(maKeHoach);

            if (countSVByMaSV == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
            else if (countByMaKeHoach == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
            }
        }
    }

    @Transactional
    public void validateGetListDangKyByMaSV(String maSV) throws BusinessException {

        if(maSV == null || "".equals(maSV)){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else {
            int countSVByMaSV = sinhVienRepository.countSinhVienByMaSV(maSV);

            if (countSVByMaSV == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
        }
    }

    @Transactional
    public void validateHuyDangKy(DangKyDto dangKyMonDto) throws BusinessException {

        if (dangKyMonDto.getMaSV() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if (sinhVienRepository.countSinhVienByMaSV(dangKyMonDto.getMaSV())== 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if (dangKyMonDto.getMaLopTcList().size() == 0){
            throw new BusinessException(MasterDataExceptionConstant.E_DANGKY_LIST_MAlOPTC_NULL);
        }
        else {
            LocalDate dateNow = LocalDate.now();
            LopTcEntity lopTcEntityEntity = lopTcService.getLopTcByMaLopTc(dangKyMonDto.getMaLopTcList().get(0));

            if(lopTcEntityEntity != null){
                KeHoachNamEntity keHoachNamEntity = keHoachNamRepository.getKeHoachNamByMaKeHoach(lopTcEntityEntity.getMaKeHoach());
                if(dateNow.isAfter(keHoachNamEntity.getTimeDkMonEnd())){
                    throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NGOAI_TIME_DK);
                }
            }
        }
    }

    @Transactional
    public void validateGetListNoMonByMaSV(String maSV) throws BusinessException {

        if(maSV == null || "".equals(maSV)){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }

    }

}
