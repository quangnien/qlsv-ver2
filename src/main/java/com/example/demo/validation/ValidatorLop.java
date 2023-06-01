package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.LopEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.LopRepository;
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
public class ValidatorLop implements Validator {

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private FunctionCommon functionCommon;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddLop(Object target) throws BusinessException {
        LopEntity lopDto = (LopEntity) target;

//        int countMaLop = lopRepository.countLopByMaLop(lopDto.getMaLop());
//        int countKhoaByMaKhoa = khoaRepository.countKhoaByMaKhoa(lopDto.getMaKhoa());
//
//        if (countKhoaByMaKhoa == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
//        }
//        else if (countMaLop > 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_LOP_DUPLICATE_MA_LOP);
//        }
    }

    @Transactional
    public void validateGetLopById(String lopId) throws BusinessException {

        int countMaLop = lopRepository.countLopById(lopId);

        if (countMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
    }

    @Transactional
    public void validateGetLopByMaLop(String maLop) throws BusinessException {

        int countMaLop = lopRepository.countLopByMaLop(maLop);

        if (countMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
    }

}
