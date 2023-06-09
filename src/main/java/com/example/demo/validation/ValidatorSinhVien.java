package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.user.SinhVienEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.SinhVienRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class ValidatorSinhVien implements Validator {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FunctionCommon functionCommon;

    @Autowired
    PasswordEncoder encoder;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddSinhVien(Object target) throws BusinessException {
        SinhVienEntity sinhVienEntity = (SinhVienEntity) target;

        int countMaSinhVien = sinhVienRepository.countSinhVienByMaSV(sinhVienEntity.getMaSV());
        int countEmail = sinhVienRepository.countSinhVienByEmail(sinhVienEntity.getEmail());
        int countLopByMaLop = lopRepository.countLopByMaLop(sinhVienEntity.getMaLop());

        if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else if (countMaSinhVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
        else if(functionCommon.isValidEmailFormat(sinhVienEntity.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
    }

    @Transactional
    public void validateEditSinhVien(Object target) throws BusinessException {
        SinhVienEntity sinhVienEntity = (SinhVienEntity) target;

        if(sinhVienEntity.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }

        Optional<SinhVienEntity> sinhVienEntityGetFromDB = sinhVienRepository.findById(sinhVienEntity.getId());
        int countLopByMaLop = lopRepository.countLopByMaLop(sinhVienEntity.getMaLop());

        if (sinhVienEntityGetFromDB.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if (countLopByMaLop == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
        }
        else {
            Long countSinhVienByMaSv = sinhVienRepository.countSinhVienByMaSVAndNotId(sinhVienEntity.getMaSV(), sinhVienEntity.getId());
            long countValueByMaSv = countSinhVienByMaSv != null ? countSinhVienByMaSv : 0;

            Long countSinhVienByEmail = sinhVienRepository.countSinhVienByEmailAndNotId(sinhVienEntity.getEmail(), sinhVienEntity.getId());
            long countValueByEmail = countSinhVienByEmail != null ? countSinhVienByEmail : 0;
            if (countValueByMaSv > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_DUPLICATE_MA_SINHVIEN);
            }
            else if (countValueByEmail > 0) {
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
            }
            else if(functionCommon.isValidEmailFormat(sinhVienEntity.getEmail()) == false){
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
            }
        }
    }

    @Transactional
    public void validateGetSinhVienById(String sinhVienId) throws BusinessException {

        int countMaSinhVien = sinhVienRepository.countSinhVienById(sinhVienId);

        if (countMaSinhVien == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
    }

    @Transactional
    public void validateGetSinhVienByMaSV(String maSV) throws BusinessException {

        int countMaSinhVien = sinhVienRepository.countSinhVienByMaSV(maSV);

        if (countMaSinhVien == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
    }

    @Transactional
    public void validateGetListSinhVienByMaLop(String maLop) throws BusinessException {

        if(maLop == null || "".equals(maLop)){
            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
        }
        else {
            int countLopByMaLop = lopRepository.countLopByMaLop(maLop);

            if (countLopByMaLop == 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
            }
        }

    }

    @Transactional
    public void validateUpdatePasswordSinhVien(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        if(updatePasswordDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
        }
        else if(updatePasswordDto.getMatKhauCu() == null || updatePasswordDto.getMatKhauCu().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_OLD);
        }
        else if(updatePasswordDto.getMatKhauMoi() == null || updatePasswordDto.getMatKhauMoi().equals("")){
            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD_NEW);
        }
//        else if(updatePasswordDto.getMatKhau() == null || updatePasswordDto.getMatKhau().equals("")){
//            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_PASSWORD);
//        }
//        else if(updatePasswordDto.getConfirmPassword() == null || updatePasswordDto.getConfirmPassword().equals("")){
//            throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_CONFIRM_PASSWORD);
//        }
        else {

            UserEntity userEntity = userService.findById(updatePasswordDto.getId());
//            UserEntity userEntity = userRepository.findById(updatePasswordDto.getId());

            if(userEntity == null){
                throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
            }
            else {
                // maSV
                String userName = userEntity.getUsername();

                SinhVienEntity getSinhVienByDB = (SinhVienEntity) sinhVienRepository.findByMaSV(userName);

                if(getSinhVienByDB == null){
                    throw new BusinessException(MasterDataExceptionConstant.E_SINHVIEN_NOT_FOUND_SINHVIEN);
                }
                else {
                    String decodedPassword = updatePasswordDto.getMatKhauCu();
                    if (! encoder.matches(decodedPassword, userEntity.getPassword())) {
                        throw new BusinessException(MasterDataExceptionConstant.E_COMMON_NOT_MATCH_PASSWORD);
                    }
                }
            }
        }
    }

//    @Transactional
//    public void validateGetTKBForSinhVien(Object target) throws BusinessException {
//
//        TkbDto tkbDto = (TkbDto) target;
//
//        if (tkbDto.getTimeInputBegin() == null) {
//            throw new BusinessException(MasterDataExceptionConstant.E_TKB_NOT_FOUND_DATE_BEGIN);
//        }
//        else if (tkbDto.getTimeInputEnd() == null) {
//            throw new BusinessException(MasterDataExceptionConstant.E_TKB_NOT_FOUND_DATE_END);
//        }
//    }

}
