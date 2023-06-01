package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.CTDTEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.CTDTRepository;
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
public class ValidatorCTDT implements Validator {

    @Autowired
    private CTDTRepository CTDTRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddCTDT(Object target) throws BusinessException {
        CTDTEntity ctdtEntity = (CTDTEntity) target;

    }

    @Transactional
    public void validateGetCTDTById(String CTDTId) throws BusinessException {

        int countMaCTDT = CTDTRepository.countCTDTById(CTDTId);

        if (countMaCTDT == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CTDT_NOT_FOUND_CTDT);
        }
    }

    @Transactional
    public void validateGetCTDTByMaCTDT(String maCTDT) throws BusinessException {

        int countMaCTDT = CTDTRepository.countCTDTByMaCTDT(maCTDT);

        if (countMaCTDT == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CTDT_NOT_FOUND_CTDT);
        }
    }

}
