package com.example.demo.validation;

import com.example.demo.common.FunctionCommon;
import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.GVDOWRepository;
import com.example.demo.repository.GiangDayRepository;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.GiangVienRepository;
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
public class ValidatorGiangVien implements Validator {

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GVDOWRepository gvdowRepository;

    @Autowired
    private GiangDayRepository giangDayRepository;

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
    public void validateAddGiangVien(Object target) throws BusinessException {
        GiangVienEntity giangVienEntity = (GiangVienEntity) target;

        int countMaGiangVien = giangVienRepository.countGiangVienByMaGV(giangVienEntity.getMaGV());
        int countEmail = giangVienRepository.countGiangVienByEmail(giangVienEntity.getEmail());
//        int countLopByMaLop = lopRepository.countLopByMaLop(giangVienEntity.getMaLop());

//        if (countLopByMaLop == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
//        }
        if (countMaGiangVien > 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
        else if(functionCommon.isValidEmailFormat(giangVienEntity.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
    }

    @Transactional
    public void validateEditGiangVien(Object target) throws BusinessException {
        GiangVienEntity giangVienEntity = (GiangVienEntity) target;

        if(giangVienEntity.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }

        Optional<GiangVienEntity> giangVienEntityGetFromDB = giangVienRepository.findById(giangVienEntity.getId());
//        int countLopByMaLop = lopRepository.countLopByMaLop(giangVienEntity.getMaLop());

        if (giangVienEntityGetFromDB.isPresent() == false) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
//        else if (countLopByMaLop == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_KHOA_NOT_FOUND_KHOA);
//        }
        else {
            Long countGiangVienByMaSv = giangVienRepository.countGiangVienByMaGVAndNotId(giangVienEntity.getMaGV(), giangVienEntity.getId());
            long countValueByMaSv = countGiangVienByMaSv != null ? countGiangVienByMaSv : 0;

            Long countGiangVienByEmail = giangVienRepository.countGiangVienByEmailAndNotId(giangVienEntity.getEmail(), giangVienEntity.getId());
            long countValueByEmail = countGiangVienByEmail != null ? countGiangVienByEmail : 0;
            if (countValueByMaSv > 0) {
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN);
            }
            else if (countValueByEmail > 0) {
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
            }
            else if(functionCommon.isValidEmailFormat(giangVienEntity.getEmail()) == false){
                throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
            }
        }
    }

    @Transactional
    public void validateGetGiangVienById(String giangVienId) throws BusinessException {

        int countMaGiangVien = giangVienRepository.countGiangVienById(giangVienId);

        if (countMaGiangVien == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
        }
    }

    @Transactional
    public void validateGetListGiangVienByMaLop(String maLop) throws BusinessException {

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
    public void validateUpdatePasswordGiangVien(UpdatePasswordDto updatePasswordDto) throws BusinessException {

        if(updatePasswordDto.getId() == null){
            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
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
                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
            }
            else {
                // maGV
                String userName = userEntity.getUsername();

                GiangVienEntity getGiangVienByDB = (GiangVienEntity) giangVienRepository.findByMaGV(userName);

                if(getGiangVienByDB == null){
                    throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
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

    @Transactional
    public boolean validateEditDKTimePossible(String maGV, String maDOW) throws BusinessException {

        Long countMaGiangVien = gvdowRepository.countByMaGVAndMaDOW(maGV, maDOW);

        if (countMaGiangVien > 0) {
            return false;
        }

        return true;
    }

    @Transactional
    public boolean validateEditDKMonHocPossible(String maGV, String maMH) throws BusinessException {

        Long countMaGiangVien = giangDayRepository.countByMaGVAndMaMH(maGV, maMH);

        if (countMaGiangVien > 0) {
            return false;
        }

        return true;
    }

//    @Transactional
//    public void validateGetTKBForGiangVien(Object target) throws BusinessException {
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
