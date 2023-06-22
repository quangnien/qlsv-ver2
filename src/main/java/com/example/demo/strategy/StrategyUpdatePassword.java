package com.example.demo.strategy;

import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.exception.BusinessException;

public interface StrategyUpdatePassword {
    void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException;
}