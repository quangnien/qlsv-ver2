package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.dto.UpdatePasswordDto;
import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.SinhVienEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.GiangVienRepository;
import com.example.demo.repository.SinhVienRepository;
import com.example.demo.service.GiangVienService;
import com.example.demo.service.SinhVienService;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidatorAdmin;
import com.example.demo.validation.ValidatorGiangVien;
import com.example.demo.validation.ValidatorSinhVien;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "DOI MAT KHAU", description = "Management APIs for DOI MAT KHAU")
public class DoiMatKhauApi {

    @Autowired
    private SinhVienService sinhVienService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private ValidatorSinhVien validatorSinhVien;

    @Autowired
    private ValidatorGiangVien validatorGiangVien;

    @Autowired
    private ValidatorAdmin validatorAdmin;

    @Operation(summary = "Update password")
    @PutMapping("/updatePassword")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SinhVienEntity.class)) })})
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update password By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* get info user is logining*/
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roleList = new ArrayList<>();
            if (principal instanceof UserDetails) {
                roleList.addAll(((UserDetails) principal).getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
            }

            if(roleList.get(0).equals("ROLE_ADMIN")){
                validatorAdmin.validateUpdatePasswordAdmin(updatePasswordDto);

                /* update PW UserEntity*/
                UserEntity userEntity = userService.findById(updatePasswordDto.getId());
                userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
                userService.updateUser(userEntity);

                returnObject.setRetObj(userEntity);
            }
            else if(roleList.get(0).equals("ROLE_GIANGVIEN")){
                validatorGiangVien.validateUpdatePasswordGiangVien(updatePasswordDto);

//                GiangVienEntity getGiangVienByDB = (GiangVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new GiangVienDto());

                UserEntity userEntity = userService.findById(updatePasswordDto.getId());
                // maSV
                String userName = userEntity.getUsername();
                GiangVienEntity getGiangVienByDB = (GiangVienEntity) giangVienRepository.findByMaGV(userName);

                /* update PW GiangVienEntity*/
                getGiangVienByDB.setPassword(updatePasswordDto.getMatKhauMoi());

                giangVienService.updateGiangVien(getGiangVienByDB);

                /* update PW UserEntity*/
//                UserEntity userEntity = userService.findByUsername(getGiangVienByDB.getMaSv());
                userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
                userService.updateUser(userEntity);

                returnObject.setRetObj(getGiangVienByDB);
            }
            else if(roleList.get(0).equals("ROLE_SINHVIEN")){
                validatorSinhVien.validateUpdatePasswordSinhVien(updatePasswordDto);

//                SinhVienEntity getSinhVienByDB = (SinhVienEntity) commonService.getObjectById(updatePasswordDto.getId(), new SinhVienDto());

                UserEntity userEntity = userService.findById(updatePasswordDto.getId());
                // maSV
                String userName = userEntity.getUsername();
                SinhVienEntity getSinhVienByDB = (SinhVienEntity) sinhVienRepository.findByMaSV(userName);

                /* update PW SinhVienEntity*/
                getSinhVienByDB.setPassword(updatePasswordDto.getMatKhauMoi());
                sinhVienService.updateExist(getSinhVienByDB);

                /* update PW UserEntity*/
//                UserEntity userEntity = userService.findByUsername(getSinhVienByDB.getMaSv());
                userEntity.setPassword(encoder.encode(updatePasswordDto.getMatKhauMoi()));
                userService.updateUser(userEntity);

                returnObject.setRetObj(getSinhVienByDB);
            }

        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(ex.getMessage());
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
