package com.example.demo.strategy;

import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.GiangVienService;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidatorGiangVien;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordGiangVien implements StrategyUpdatePassword{
    @Autowired
    private ValidatorGiangVien validatorGiangVien;
    @Autowired
    private UserService userService;
    @Autowired
    private GiangVienService giangVienService;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        // validate
        System.out.println("Validate received data ::: Lecturer ::: " + updatePasswordDto.getUserName());
        validatorGiangVien.validateUpdatePasswordGiangVien(updatePasswordDto);

        // updatePasswordUser ...
        System.out.println("Update password ::: Lecturer ::: newPW: " + updatePasswordDto.getMatKhauMoi());

        UserEntity userEntity = userService.findByUsername(updatePasswordDto.getUserName());
        GiangVienEntity getGiangVienByDB = giangVienService.findByMaGV(updatePasswordDto.getUserName());

        /* update PW GiangVienEntity*/
        getGiangVienByDB.setPassword(updatePasswordDto.getMatKhauMoi());

        giangVienService.updateGiangVien(getGiangVienByDB);

        /* update PW UserEntity*/
        userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
        userService.updateUser(userEntity);
    }
}
