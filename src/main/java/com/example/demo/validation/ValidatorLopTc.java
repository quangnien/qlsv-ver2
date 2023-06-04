//package com.example.demo.validation;
//
//import com.example.demo.common.FunctionCommon;
//import com.example.demo.exception.BusinessException;
//import com.example.demo.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
///**
// * @author NienNQ
// * @created 2023 - 03 - 05 11:02 AM
// * @project qlsv
// */
//@Component
//public class ValidatorLopTc implements Validator {
//
//    @Autowired
//    private LopTcRepository dsLopTcRepository;
//
//    @Autowired
//    private GiangVienRepository giangVienRepository;
//
//    @Autowired
//    private LopRepository lopRepository;
//
//    @Autowired
//    private MonHocRepository monHocRepository;
//
//    @Autowired
//    private FunctionCommon functionCommon;
//
//    @Autowired
//    private KeHoachNamRepository keHoachNamRepository;
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return false;
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//    }
//
//    @Transactional
//    public void validateAddLopTc(Object target) throws BusinessException {
//        LopTcDto dsLopTcDto = (LopTcDto) target;
//
//        int countMaLopTc = dsLopTcRepository.countLopTcByMaLopTc(dsLopTcDto.getMaLopTc());
//        int countGVByMaGV = giangVienRepository.countGiangVienByMaGV(dsLopTcDto.getMaGV());
//        int countLopByMaLop = lopRepository.countLopByMaLop(dsLopTcDto.getMaLop());
//        int countMonHocByMaMH = monHocRepository.countMonHocByMaMH(dsLopTcDto.getMaMH());
//        int countKeHoachNamByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());
//
//        if (countGVByMaGV == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//        }
//        if (countLopByMaLop == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
//        }
//        if (countMonHocByMaMH == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
//        }
//        else if (countKeHoachNamByMaKeHoach == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//        }
//        else if (countMaLopTc > 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_DUPLICATE_MA_DSLOPTC);
//        }
//    }
//
//    @Transactional
//    public void validateEditLopTc(Object target) throws BusinessException {
//
//        LopTcDto dsLopTcDto = (LopTcDto) target;
//
//        if(dsLopTcDto.getId() == null){
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//
//        Optional<LopTcEntity> dsLopTcEntity = dsLopTcRepository.findById(dsLopTcDto.getId());
//        int countGVByMaGV = giangVienRepository.countGiangVienByMaGV(dsLopTcDto.getMaGV());
//        int countLopByMaLop = lopRepository.countLopByMaLop(dsLopTcDto.getMaLop());
//        int countMonHocByMaMH = monHocRepository.countMonHocByMaMH(dsLopTcDto.getMaMH());
//        int countKeHoachNamByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());
//
//        if (dsLopTcEntity.isPresent() == false) {
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//        else if (countGVByMaGV == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//        }
//        else if (countLopByMaLop == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
//        }
//        else if (countMonHocByMaMH == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_MONHOC_NOT_FOUND_MONHOC);
//        }
//        else if (countKeHoachNamByMaKeHoach == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//        }
//        else {
//            Long countMaLopTc = dsLopTcRepository.countLopTcByMaLopTcAndNotId(dsLopTcDto.getMaLopTc(), dsLopTcDto.getId());
//            long countValue = countMaLopTc != null ? countMaLopTc : 0;
//            if (countValue > 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_DUPLICATE_MA_DSLOPTC);
//            }
//        }
//    }
//
//    @Transactional
//    public void validateGetLopTcById(String dsLopTcId) throws BusinessException {
//
//        int countMaLopTc = dsLopTcRepository.countLopTcById(dsLopTcId);
//
//        if (countMaLopTc == 0) {
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NOT_FOUND_DSLOPTC);
//        }
//    }
//
//    @Transactional
//    public void validateGetListLopTcByMaLop(String maLop) throws BusinessException {
//
//        if(maLop == null || "".equals(maLop)){
//            throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
//        }
//        else {
//            int countLopByMaLop = lopRepository.countLopByMaLop(maLop);
//
//            if (countLopByMaLop == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_LOP_NOT_FOUND_LOP);
//            }
//        }
//
//    }
//
//    @Transactional
//    public void validateSearchThongKe(String idKeHoachNam, String keySearch) throws BusinessException {
//
//        if(idKeHoachNam == null || "".equals(idKeHoachNam)){
//            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//        }
//        else {
//            int countKeHoachNam = keHoachNamRepository.countKeHoachNamById(idKeHoachNam);
//
//            if (countKeHoachNam == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//            }
//        }
//    }
//
//    @Transactional
//    public void validateGetListLopTcByMaGVAndMaKeHoach(String maGV, String maKeHoach) throws BusinessException {
//
//        if(maGV == null || "".equals(maGV)){
//            throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//        }
//        else if(maKeHoach == null || "".equals(maKeHoach)){
//            throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//        }
//        else {
//            int countGVMaGV = giangVienRepository.countGiangVienByMaGV(maGV);
//            int countByMaKeHoach = keHoachNamRepository.countKeHoachNamByMaKeHoach(maKeHoach);
//
//            if (countGVMaGV == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_GIANGVIEN_NOT_FOUND_GIANGVIEN);
//            }
//            else if (countByMaKeHoach == 0) {
//                throw new BusinessException(MasterDataExceptionConstant.E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM);
//            }
//        }
//    }
//
//    @Transactional
//    public void validateDeleteLopTc(Object target) throws BusinessException {
//
//        LopTcDto dsLopTcDto = (LopTcDto) target;
//
////        Date dateNow = new Date();
//        LocalDate dateNow = LocalDate.now();
//
//        KeHoachNamEntity keHoachNamEntity = keHoachNamRepository.getKeHoachNamByMaKeHoach(dsLopTcDto.getMaKeHoach());
//        if(dateNow.isAfter(keHoachNamEntity.getTimeDkMonEnd())){
//            throw new BusinessException(MasterDataExceptionConstant.E_DSLOPTC_NGOAI_TIME_DK);
//        }
//    }
//}
