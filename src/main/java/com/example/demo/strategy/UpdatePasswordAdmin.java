package com.example.demo.strategy;

import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidatorAdmin;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordAdmin implements StrategyUpdatePassword{
    @Autowired
    private UserService userService;
    @Autowired
    private ValidatorAdmin validatorAdmin;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Student ::: " + updatePasswordDto.getUserName());
        validatorAdmin.validateUpdatePasswordAdmin(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Student ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        /* update PW UserEntity*/
        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);

    }
}
