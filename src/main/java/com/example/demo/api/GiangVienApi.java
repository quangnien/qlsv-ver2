package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.dto.GVDOWDto;
import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.GvDowEntity;
import com.example.demo.service.GVDOWService;
import com.example.demo.service.GiangVienService;
import com.example.demo.service.UserService;
import com.example.demo.validation.ValidatorGiangVien;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Giang Vien", description = "Management APIs for GIANG VIEN.")
public class GiangVienApi {

    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ValidatorGiangVien validatorGiangVien;

    @Autowired
    private GVDOWService gvdowService;

    /* CREATE */
    @Operation(summary = "Create Giang Vien.")
    @PostMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> createGiangVien(@Valid @RequestBody GiangVienEntity giangVienEntity, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateAddGiangVien(giangVienEntity);
            GiangVienEntity giangVienEntityResult = giangVienService.addNew(giangVienEntity);
            returnObject.setRetObj(giangVienEntityResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update Giang Vien.")
    @PutMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_GIANGVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> updateGiangVien(@Valid @RequestBody GiangVienEntity giangVienEntity, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateEditGiangVien(giangVienEntity);
            giangVienService.updateGiangVien(giangVienEntity);

            returnObject.setRetObj(giangVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete Giang Vien by list id")
    @DeleteMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> deleteGiangVien(@Valid @RequestBody List<String> listGiangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = giangVienService.deleteListGiangVien(listGiangVienId);
            returnObject.setRetObj(deleteSuccess);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all Giang Vien.")
    @GetMapping("/giangVien")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> getAllGiangVien() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<GiangVienEntity> listGiangVien = giangVienService.findAll();
            returnObject.setRetObj(listGiangVien);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get Giang Vien by id.")
    @GetMapping("/giangVien/{giangVienId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_GIANGVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> getGiangVienById(@PathVariable String giangVienId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get GiangVien By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorGiangVien.validateGetGiangVienById(giangVienId);

            GiangVienEntity giangVienEntity = giangVienService.findById(giangVienId);
            returnObject.setRetObj(giangVienEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* ĐK TIME POSSIBLE  */
    @Operation(summary = "Đang ky Day Of Week.")
    @PostMapping("/giangVien/dow")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
    public ResponseEntity<?> createGiangVienDOW(@Valid @RequestBody GVDOWDto gvdowDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add Day of week for GiangVien!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            GVDOWDto gvdowDtoValid = new GVDOWDto();
            List<String> maDOWList = new ArrayList<>();

            String maGV = gvdowDto.getMaGV();
            for(String maDOW: gvdowDto.getMaDOWList()){
                GvDowEntity gvDowEntity = new GvDowEntity();
                gvDowEntity.setMaGV(maGV);
                gvDowEntity.setMaDOW(maDOW);

                boolean isValid = validatorGiangVien.validateEditDKTimePossible(maGV, maDOW);
                if(isValid == true){
                    maDOWList.add(maDOW);
                }
            }

            gvdowDtoValid.setMaGV(gvdowDto.getMaGV());
            gvdowDtoValid.setMaDOWList(maDOWList);

            GVDOWDto gvdowDtoResult = gvdowService.updateExist(gvdowDtoValid);

            returnObject.setRetObj(gvdowDtoResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

//    @Operation(summary = "Get Giang Vien by maLop.")
//    @GetMapping("/giangVien/lop/{maLop}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_GIANGVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GiangVienEntity.class)) })})
//    public ResponseEntity<?> getGiangVienByLopId(@PathVariable String maLop,
//                                                @RequestParam(defaultValue = "0") int page,
//                                                @RequestParam(defaultValue = "12") int size) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get GiangVien By maLop!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorGiangVien.validateGetListGiangVienByMaLop(maLop);
//            List<GiangVienEntity> giangVienEntity = giangVienService.findAllByMaLop(maLop, page, size);
//            returnObject.setRetObj(giangVienEntity);
//
//            /*for paging*/
//            List<GiangVienEntity> dsLopTcEntityForPaging = giangVienService.findAllByMaLop(maLop, 0, 100000);
//
//            double totalPageDouble = (double) dsLopTcEntityForPaging.size() / size;
//            int totalPageForPaging = (int) Math.ceil(totalPageDouble);
//
//            returnObject.setPage(page);
//            returnObject.setTotalRetObjs(dsLopTcEntityForPaging.size());
//            returnObject.setTotalPages(totalPageForPaging);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }
}
