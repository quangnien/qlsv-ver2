package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.ChuyenNganhEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.ChuyenNganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 11:02 AM
 * @project qlsv
 */
@Component
public class ValidatorChuyenNganh implements Validator {

    @Autowired
    private ChuyenNganhRepository chuyenNganhRepository;

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
    public void validateAddChuyenNganh(Object target) throws BusinessException {
        ChuyenNganhEntity chuyenNganhDto = (ChuyenNganhEntity) target;
    }

    @Transactional
    public void validateGetChuyenNganhById(String chuyenNganhId) throws BusinessException {

        int countMaChuyenNganh = chuyenNganhRepository.countChuyenNganhById(chuyenNganhId);

        if (countMaChuyenNganh == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CHUYENNGANH_NOT_FOUND_CHUYENNGANH);
        }
    }

    @Transactional
    public void validateGetChuyenNganhByMaChuyenNganh(String maCN) throws BusinessException {

        int countMaChuyenNganh = chuyenNganhRepository.countChuyenNganhByMaCN(maCN);

        if (countMaChuyenNganh == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CHUYENNGANH_NOT_FOUND_CHUYENNGANH);
        }
    }

}
