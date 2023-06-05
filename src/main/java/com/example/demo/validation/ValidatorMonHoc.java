package com.example.demo.validation;

import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.MHTQRepository;
import com.example.demo.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorMonHoc implements Validator {

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private MHTQRepository mhtqRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddMonHoc(Object target) throws BusinessException {
        MonHocEntity monHocDto = (MonHocEntity) target;

        int countMaMonHoc = monHocRepository.countMonHocByMaMH(monHocDto.getMaMH());

        if (countMaMonHoc > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
        }
    }

    @Transactional
    public void validateEditMonHoc(Object target) throws BusinessException {
        MonHocEntity monHocDto = (MonHocEntity) target;

        if(monHocDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }

        Optional<MonHocEntity> monHocEntity = monHocRepository.findById(monHocDto.getId());

        if (monHocEntity.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
        else {
            Long countMaMonHoc = monHocRepository.countMonHocByMaMHAndNotId(monHocDto.getMaMH(), monHocDto.getId());
            long countValue = countMaMonHoc != null ? countMaMonHoc : 0;
            if (countValue > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_DUPLICATE_MA_MONHOC);
            }
        }
    }

    @Transactional
    public void validateGetMonHocById(String monHocId) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocById(monHocId);

        if (countMaMonHoc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
    }

    @Transactional
    public void validateGetMonHocByMaMH(String maMH) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocByMaMH(maMH);

        if (countMaMonHoc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
        }
    }

    @Transactional
    public boolean validateUpdateDKMHTQPossible(String maMH, String maMHTQ) throws BusinessException {

        int countMaMonHoc = monHocRepository.countMonHocByMaMH(maMH);
        int countMaMonHocTienQuyet = monHocRepository.countMonHocByMaMH(maMHTQ);

        Long countMHMHTQ = mhtqRepository.countMHTQByMaMHAndMaMHTQ(maMH, maMHTQ);

        if (countMHMHTQ > 0) {
            return false;
        }
        else if(maMH.equals(maMHTQ)){
            return false;
        }
        else if (countMaMonHoc == 0) {
            return false;
        }
        else if (countMaMonHocTienQuyet == 0) {
            return false;
        }

        return true;
    }

    @Transactional
    public void validateGetListMonHocByMaLop(String maLop) throws BusinessException {

        if(maLop == null || "".equals(maLop)){
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else {
            int countLopByMaLop = lopRepository.countLopByMaLop(maLop);

            if (countLopByMaLop == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
            }
        }

    }

}
