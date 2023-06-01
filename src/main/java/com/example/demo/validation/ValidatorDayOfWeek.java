package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.DayOfWeekRepository;
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
public class ValidatorDayOfWeek implements Validator {

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

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
    public void validateAddDayOfWeek(Object target) throws BusinessException {
        DayOfWeekEntity dayOfWeekDto = (DayOfWeekEntity) target;
    }

    @Transactional
    public void validateGetDayOfWeekById(String dayOfWeekId) throws BusinessException {

        int countMaDayOfWeek = dayOfWeekRepository.countDayOfWeekById(dayOfWeekId);

        if (countMaDayOfWeek == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DAYOFWEEK_NOT_FOUND_DAYOFWEEK);
        }
    }

    @Transactional
    public void validateGetDayOfWeekByMaDOW(String maDOW) throws BusinessException {

        int countMaDayOfWeek = dayOfWeekRepository.countDayOfWeekByMaDOW(maDOW);

        if (countMaDayOfWeek == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_DAYOFWEEK_NOT_FOUND_DAYOFWEEK);
        }
    }

}
