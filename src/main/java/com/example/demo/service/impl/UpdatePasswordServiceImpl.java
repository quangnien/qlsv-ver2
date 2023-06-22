package com.example.demo.service.impl;

import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.UpdatePasswordService;
import com.example.demo.strategy.StrategyUpdatePassword;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdatePasswordServiceImpl implements UpdatePasswordService {

    StrategyUpdatePassword strategy;

    public void setStrategyUpdatePassword(StrategyUpdatePassword strategyUpdatePassword) {
        this.strategy= strategyUpdatePassword;
    }

    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {
        strategy.updatePassword(updatePasswordDto);
    }

}