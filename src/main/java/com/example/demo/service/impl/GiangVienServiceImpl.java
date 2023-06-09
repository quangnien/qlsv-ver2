package com.example.demo.service.impl;

import com.example.demo.common.FunctionCommon;
import com.example.demo.entity.user.ERole;
import com.example.demo.entity.user.GiangVienEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GiangVienService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private FunctionCommon functionCommon;

    //CRUD  CREATE , READ , UPDATE , DELETE

    @Override
    public GiangVienEntity addNew(GiangVienEntity giangVienEntity) {
        
        giangVienEntity.setPassword("123456");

        /*_______________________________________________*/
        /* BEGIN CREATE ACCOUNT USER WITH ROLE_GIANGVIEN */
        /*_______________________________________________*/
        SignupRequest signUpRequest = functionCommon.createUserAccountTemp(giangVienEntity);

        // Create new user's account

        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserId(), signUpRequest.getUserFullName());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;

                    case "SINHVIEN":
                        RoleEntity sinhvienRole = roleRepository.findByName(ERole.ROLE_SINHVIEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(sinhvienRole);
                        break;

                    case "GIANGVIEN":
                        RoleEntity giangvienRole = roleRepository.findByName(ERole.ROLE_GIANGVIEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(giangvienRole);
                        break;
                }
            });
        }
        giangVienEntity.setId(UUID.randomUUID().toString().split("-")[0]);

        user.setRoles(roles);
        user.setUserId(giangVienEntity.getId());
        user.setUserFullName(giangVienEntity.getHo() + " " + giangVienEntity.getTen());
        userRepository.save(user);
        /*_____________________________________________*/
        /* END CREATE ACCOUNT USER WITH ROLE_GIANGVIEN */
        /*_____________________________________________*/

        return giangVienRepository.save(giangVienEntity);
    }

    @Override
    public GiangVienEntity updateGiangVien(GiangVienEntity giangVienEntity) {
        return giangVienRepository.save(giangVienEntity);
    }

    @Override
    public GiangVienEntity findById(String id) {
        return giangVienRepository.findById(id).get();
    }

    @Override
    public GiangVienEntity findByMaGV(String maGV) {
        return giangVienRepository.findByMaGV(maGV);
    }

    @Override
    public List<GiangVienEntity> findAll() {
        return giangVienRepository.findAll();
    }

    @Override
    public List<GiangVienEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return giangVienRepository.findAll(pageable).getContent();
    }

    @Override
    public List<String> deleteListGiangVien(List<String> listGiangVienId) {
        List<String> lstSuccess = new ArrayList<>();
        for (String item : listGiangVienId) {
            int countMaGiangVien = giangVienRepository.countGiangVienById(item);
            if(countMaGiangVien > 0){
                lstSuccess.add(item);
                giangVienRepository.deleteById(item);
            }
        }
        return lstSuccess;
    }

//    @Override
//    public List<WrapTkbDto> getListTKBForGV(String maGiangVien, String maKeHoach, int tuan) {
//        List<TkbDto> tkbDtoList = new ArrayList<>();
//
//        KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamByMaKeHoach(maKeHoach);
//
//        LocalDate timeTuanBdParam = keHoachNamEntity.getTimeStudyBegin().plusDays((tuan - 1) * 7);
//        LocalDate timeTuanKtParam = timeTuanBdParam.plusDays(7);
//
//        List<DsLopTcEntity> dsLopTcEntityList = dsLopTcRepository.findAllByMaGvAndMaKeHoach(maGiangVien, maKeHoach);
//        for(DsLopTcEntity dsLopTcEntity: dsLopTcEntityList){
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
//            if(chiTietLopTcEntityListValid != null){
//                for (ChiTietLopTcEntity chiTietLopTcEntity: chiTietLopTcEntityListValid){
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
//                    GiangVienEntity giangVienEntity = giangVienRepository.findByMaGv(maGiangVien);
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
//        for (String thu: thuOfWeek){
//            List<TkbDto> tkbDtos = new ArrayList<>();
//            WrapTkbDto wrapTkbDto = new WrapTkbDto();
//
//            for (TkbDto tkbDto: tkbDtoList){
//                wrapTkbDto.setThu(thu);
//                if(tkbDto.getThu().equals(thu)){
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

//    @Override
//    public List<GiangVienEntity> findByMaKhoa(int page, int size, String maKhoa) {
//        Pageable pageable = PageRequest.of(page, size);
//        return giangVienRepository.findAllByMaKhoa(maKhoa, pageable).getContent();
//    }

}
