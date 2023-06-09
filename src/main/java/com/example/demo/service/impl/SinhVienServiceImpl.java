package com.example.demo.service.impl;

import com.example.demo.common.FunctionCommon;
import com.example.demo.entity.*;
import com.example.demo.entity.user.ERole;
import com.example.demo.entity.user.SinhVienEntity;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SinhVienRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SinhVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    PasswordEncoder encoder;

    private FunctionCommon functionCommon;

//    @Override
//    public List<WrapTkbDto> getListTKBForSv(String maSinhVien, String maKeHoach, int tuan) {
//        List<TkbDto> tkbDtoList = new ArrayList<>();
//
//        // từ maSinhVien -> tìm maGiangVien -> chuyển về bài toán getTkbByMaGiangVien
//
//        // Ở dưới xong ta sẽ có 1 list các maLopTc
//        List<DiemEntity> diemEntityList = diemRepository.getListDiemByMaSv(maSinhVien);
//
//        List<DsLopTcEntity> dsLopTcEntityListBanDau = new ArrayList<>();
//        for (DiemEntity diemEntity : diemEntityList) {
//            dsLopTcEntityListBanDau.add(dsLopTcRepository.getDsLopTcByMaLopTc(diemEntity.getMaLopTc()));
//        }
//
//        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamByMaKeHoach(maKeHoach);
//
//        LocalDate timeTuanBdParam = keHoachNamEntity.getTimeStudyBegin().plusDays((tuan - 1) * 7);
//        LocalDate timeTuanKtParam = timeTuanBdParam.plusDays(7);
//
//        List<DsLopTcEntity> dsLopTcEntityList = new ArrayList<>();
//        for (DsLopTcEntity dsLopTcEntity : dsLopTcEntityListBanDau) {
//            if (dsLopTcEntity.getMaKeHoach().equals(maKeHoach)) {
//                dsLopTcEntityList.add(dsLopTcEntity);
////            dsLopTcRepository.findAllByMaGvAndMaKeHoach(maGiangVien, maKeHoach);
//            }
//        }
//
//        for (DsLopTcEntity dsLopTcEntity : dsLopTcEntityList) {
//
//            Sort sort = Sort.by(
//                    Sort.Order.asc("thu"),
//                    Sort.Order.asc("tiet")
//            );
//            List<ChiTietLopTcEntity> chiTietLopTcEntityList = chiTietLopTcRepository.findAllByMaLopTc(dsLopTcEntity.getMaLopTc(), sort);
//            List<ChiTietLopTcEntity> chiTietLopTcEntityListValid = new ArrayList<>();
//
//            for (ChiTietLopTcEntity chiTietLopTcEntity : chiTietLopTcEntityList) {
//                if (timeTuanBdParam.isEqual(chiTietLopTcEntity.getTimeBd()) || timeTuanBdParam.isAfter(chiTietLopTcEntity.getTimeBd())
//                        && (timeTuanKtParam.isEqual(chiTietLopTcEntity.getTimeKt()) || timeTuanKtParam.isBefore(chiTietLopTcEntity.getTimeKt()))) {
//                    chiTietLopTcEntityListValid.add(chiTietLopTcEntity);
//                }
//            }
//
//            if (chiTietLopTcEntityListValid != null) {
//                for (ChiTietLopTcEntity chiTietLopTcEntity : chiTietLopTcEntityListValid) {
//
//                    ModelMapper modelMapper = new ModelMapper();
//                    TkbDto tkbDto = modelMapper.map(dsLopTcEntity, TkbDto.class);
//
//                    // bonus TEN MON HOC
//                    String tenMh = "";
//                    MonHocEntity monHocEntity = monHocRepository.getMonHocByMaMh(dsLopTcEntity.getMaMh());
//                    tenMh = monHocEntity.getTenMh();
//                    tkbDto.setTenMh(tenMh);
//
//                    // bonus TEN GIANG VIEN
//                    String tenGv = "";
//                    GiangVienEntity giangVienEntity = giangVienRepository.findByMaGv(dsLopTcEntity.getMaGv());
//                    tenGv = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                    tkbDto.setTenGv(tenGv);
//
//                    tkbDto.setTiet(chiTietLopTcEntity.getTiet());
//                    tkbDto.setSoTiet(chiTietLopTcEntity.getSoTiet());
//                    tkbDto.setThu(chiTietLopTcEntity.getThu());
//                    tkbDto.setPhong(chiTietLopTcEntity.getPhong());
//
//                    tkbDtoList.add(tkbDto);
//                }
//            }
//
//        }
//
//        List<WrapTkbDto> wrapTkbDtoList = new ArrayList<>();
//
//        List<String> thuOfWeek = new ArrayList<>();
//        String thu2 = "02";
//        String thu3 = "03";
//        String thu4 = "04";
//        String thu5 = "05";
//        String thu6 = "06";
//        String thu7 = "07";
//        thuOfWeek.add(thu2);
//        thuOfWeek.add(thu3);
//        thuOfWeek.add(thu4);
//        thuOfWeek.add(thu5);
//        thuOfWeek.add(thu6);
//        thuOfWeek.add(thu7);
//
//        for (String thu : thuOfWeek) {
//            List<TkbDto> tkbDtos = new ArrayList<>();
//            WrapTkbDto wrapTkbDto = new WrapTkbDto();
//
//            for (TkbDto tkbDto : tkbDtoList) {
//                wrapTkbDto.setThu(thu);
//                if (tkbDto.getThu().equals(thu)) {
//                    tkbDtos.add(tkbDto);
//                }
//            }
//            wrapTkbDto.setTkbDtoList(tkbDtos);
//
//            wrapTkbDtoList.add(wrapTkbDto);
//        }
//
//        return wrapTkbDtoList;
//    }

    @Override
    public SinhVienEntity addNew(SinhVienEntity sinhVienEntity) {

        sinhVienEntity.setPassword("123456");

        // set TEN LOP
        LopEntity lopEntity = lopRepository.findByMaLop(sinhVienEntity.getMaLop());
        sinhVienEntity.setTenLop(lopEntity.getTenLop());

        /*_____________________________________________*/
        /* BEGIN CREATE ACCOUNT USER WITH ROLE_SINHVIEN*/
        /*_____________________________________________*/

        // Create new user's account
        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserId(), signUpRequest.getUserFullName());

        /*____________________________________________*/
        /* END CREATE ACCOUNT USER WITH ROLE_SINHVIEN */
        /*____________________________________________*/

//        SignupRequest signUpRequest = functionCommon.createUserAccountTemp(sinhVienEntity);
//
//        // Create new user's account
//        UserEntity user = new UserEntity(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserId(), signUpRequest.getUserFullName());
//
//        Set<String> strRoles = signUpRequest.getRoles();
//        Set<RoleEntity> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "ADMIN":
//                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//                        break;
//
//                    case "SINHVIEN":
//                        RoleEntity sinhvienRole = roleRepository.findByName(ERole.ROLE_SINHVIEN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(sinhvienRole);
//                        break;
//
//                    case "GIANGVIEN":
//                        RoleEntity giangvienRole = roleRepository.findByName(ERole.ROLE_GIANGVIEN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(giangvienRole);
//                        break;
//                }
//            });
//        }
//
//        sinhVienEntity.setId(UUID.randomUUID().toString().split("-")[0]);
//
//        user.setRoles(roles);
//        user.setUserId(sinhVienEntity.getId());
//        user.setUserFullName(sinhVienEntity.getHo() + " " + sinhVienEntity.getTen());
//        userRepository.save(user);
//        /*____________________________________________*/
//        /* END CREATE ACCOUNT USER WITH ROLE_SINHVIEN */
//        /*____________________________________________*/

        return sinhVienRepository.save(sinhVienEntity);
    }

    @Override
    public SinhVienEntity updateExist(SinhVienEntity sinhVienEntity) {

        // set TEN LOP
        LopEntity lopEntity = lopRepository.findByMaLop(sinhVienEntity.getMaLop());
        sinhVienEntity.setTenLop(lopEntity.getTenLop());

        return sinhVienRepository.save(sinhVienEntity);
    }

    @Override
    public List<String> deleteListSinhVien(List<String> listId) {
        for (String item : listId) {
            int countMaSinhVien = sinhVienRepository.countSinhVienById(item);
            if(countMaSinhVien > 0){

                /* important: validator */
                SinhVienEntity sinhVienEntity = sinhVienRepository.findById(item).get();
                //  TODO
//                int countSinhVienOnDiemTable = diemRepository.countDiemByMaSv(sinhVienEntity.getMaSv());

//                if(countSinhVienOnDiemTable > 0){
//                    continue;
//                }
//                else {
//                    lstSuccess.add(item);
//                    sinhVienRepository.deleteById(item);
//                }

            }
        }
        return null;
    }

    @Override
    public List<SinhVienEntity> findAll() {
        return sinhVienRepository.findAll();
    }

    @Override
    public SinhVienEntity findById(String id) {
        return sinhVienRepository.findById(id).get();
    }

    @Override
    public List<SinhVienEntity> findAllByMaLop(String maLop, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<SinhVienEntity> resultPage = sinhVienRepository.findAllByMaLop(maLop, pageable);
        return resultPage.getContent();
    }

    @Override
    public SinhVienEntity findByMaSV(String maSV) {
        return sinhVienRepository.findByMaSV(maSV);
    }

}
