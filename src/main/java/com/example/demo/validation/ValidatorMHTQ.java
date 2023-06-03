package com.example.demo.validation;

import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.exception.BusinessException;
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
public class ValidatorMHTQ implements Validator {

    @Autowired
    private MonHocRepository monHocRepository;

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
    public void validateGetMHTQById(String monHocId) throws BusinessException {

        int countMaMonHoc = mhtqRepository.countMHTQById(monHocId);

        if (countMaMonHoc == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_MONHOCTIENQUYET_NOT_FOUND_MONHOCTIENQUYET);
        }
    }

}
