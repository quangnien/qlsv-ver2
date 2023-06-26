package com.example.demo.strategy;

import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.SinhVienEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.SinhVienService;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidatorSinhVien;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdatePasswordSinhVien implements StrategyUpdatePassword{

    @Autowired
    private ValidatorSinhVien validatorSinhVien;
    @Autowired
    private UserService userService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Student ::: " + updatePasswordDto.getUserName());
        validatorSinhVien.validateUpdatePasswordSinhVien(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Student ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        SinhVienEntity getSinhVienByDB = sinhVienService.findByMaSV(updatePasswordDto.getUserName());

        /* update PW SinhVienEntity */
        getSinhVienByDB.setPassword(updatePasswordDto.getMatKhauMoi());

        sinhVienService.updateExist(getSinhVienByDB);

        /* update PW UserEntity*/
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
        
    }

}
