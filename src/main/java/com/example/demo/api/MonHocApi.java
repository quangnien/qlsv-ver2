package com.example.demo.api;

import com.example.demo.common.FunctionCommon;
import com.example.demo.common.ReturnObject;
import com.example.demo.dto.MHMHTQDto;
import com.example.demo.dto.MonHocModifyDto;
import com.example.demo.entity.MHTQEntity;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.service.MHTQService;
import com.example.demo.service.MonHocService;
import com.example.demo.validation.ValidatorMonHoc;
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
@Tag(name = "Mon Hoc", description = "Management APIs for MON HOC.")
public class MonHocApi {

    @Autowired
    private MonHocService monHocService;

    @Autowired
    private MHTQService mhtqService;

    @Autowired
    private ValidatorMonHoc validatorMonHoc;

    @Autowired
    private FunctionCommon functionCommon;

    /* CREATE */
    @Operation(summary = "Create MonHoc.")
    @PostMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> createMonHoc(@Valid @RequestBody MonHocModifyDto monHocModifyDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            MonHocEntity monHocEntity = new MonHocEntity();
            monHocEntity.setMaMH(monHocModifyDto.getMaMH());
            monHocEntity.setTenMH(monHocModifyDto.getTenMH());

            /* STEP1: ADD NEW */
            validatorMonHoc.validateAddMonHoc(monHocEntity);
            MonHocEntity monHocEntityResult = monHocService.addNew(monHocEntity);

            MonHocModifyDto monHocModifyDtoResult = new MonHocModifyDto();
            /* STEP2: IF STEP1 SUCCESS -> ADD TO TABLE MHTQ */
            if(monHocModifyDto.getMaMHTQList() != null ){
                MHMHTQDto mhmhtqDtoValid = new MHMHTQDto();
                List<String> maMHTQList = new ArrayList<>();

                String maMH = monHocModifyDto.getMaMH();
                for(String maMHTQ: functionCommon.convertListToSetToList(monHocModifyDto.getMaMHTQList())){

                    boolean isValid = validatorMonHoc.validateUpdateDKMHTQPossible(maMH, maMHTQ);
                    if(isValid == true){
                        maMHTQList.add(maMHTQ);
                    }
                }

                mhmhtqDtoValid.setMaMH(monHocModifyDto.getMaMH());
                mhmhtqDtoValid.setMaMHTQList(maMHTQList);

                mhtqService.updateExist(mhmhtqDtoValid);

                monHocModifyDtoResult.setMaMHTQList(maMHTQList);
            }
            else {
                monHocModifyDtoResult.setMaMHTQList(null);
            }
            /* END STEP2 */

            monHocModifyDtoResult.setMaMH(monHocModifyDto.getMaMH());
            monHocModifyDtoResult.setTenMH(monHocModifyDto.getTenMH());
            monHocModifyDtoResult.setId(monHocEntityResult.getId());

            returnObject.setRetObj(monHocModifyDtoResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @Operation(summary = "Update MonHoc.")
    @PutMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> updateMonHoc(@Valid @RequestBody MonHocModifyDto monHocModifyDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            MonHocEntity monHocEntity = new MonHocEntity();
            monHocEntity.setMaMH(monHocModifyDto.getMaMH());
            monHocEntity.setTenMH(monHocModifyDto.getTenMH());
            monHocEntity.setId(monHocModifyDto.getId());

            /* STEP1: EDIT EXIST */
            validatorMonHoc.validateEditMonHoc(monHocEntity);
            monHocService.updateExist(monHocEntity);

            MonHocModifyDto monHocModifyDtoResult = new MonHocModifyDto();
            /* STEP2: IF STEP1 SUCCESS -> ADD TO TABLE MHTQ */
            if(monHocModifyDto.getMaMHTQList() != null ){

                /* REMOVE ALL -> MAMH */
                List<MHTQEntity> mhtqEntityList = mhtqService.findAllByMaMH(monHocModifyDto.getMaMH());
                if(mhtqEntityList != null){
                    for(MHTQEntity item : mhtqEntityList){
                        mhtqService.deleteRecord(item.getId());
                    }
                }
                /* END REMOVE ALL -> MAMH */

                MHMHTQDto mhmhtqDtoValid = new MHMHTQDto();
                List<String> maMHTQList = new ArrayList<>();

                String maMH = monHocModifyDto.getMaMH();
                for(String maMHTQ: functionCommon.convertListToSetToList(monHocModifyDto.getMaMHTQList())){

                    boolean isValid = validatorMonHoc.validateUpdateDKMHTQPossible(maMH, maMHTQ);
                    if(isValid == true){
                        maMHTQList.add(maMHTQ);
                    }
                }

                mhmhtqDtoValid.setMaMH(monHocModifyDto.getMaMH());
                mhmhtqDtoValid.setMaMHTQList(maMHTQList);

                mhtqService.updateExist(mhmhtqDtoValid);

                monHocModifyDtoResult.setMaMHTQList(maMHTQList);
            }
            else {
                monHocModifyDtoResult.setMaMHTQList(null);
            }
            /* END STEP2 */

            monHocModifyDtoResult.setMaMH(monHocModifyDto.getMaMH());
            monHocModifyDtoResult.setTenMH(monHocModifyDto.getTenMH());
            monHocModifyDtoResult.setId(monHocModifyDtoResult.getId());

            returnObject.setRetObj(monHocModifyDtoResult);

        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* DELETE */
    @Operation(summary = "Delete MonHoc by list id")
    @DeleteMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> deleteMonHoc(@Valid @RequestBody List<String> lstMonHocId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete List MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<String> deleteSuccess = monHocService.deleteList(lstMonHocId);
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
    @Operation(summary = "Get all MonHoc.")
    @GetMapping("/monHoc")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getAllMonHoc() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All MonHoc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<MonHocEntity> monHocEntityList = monHocService.findAll();
            returnObject.setRetObj(monHocEntityList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get MonHoc by id.")
    @GetMapping("/monHoc/{monHocId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getMonHocById(@PathVariable String monHocId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MonHoc By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetMonHocById(monHocId);
            MonHocEntity monHocEntity = monHocService.findById(monHocId);
            returnObject.setRetObj(monHocEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY MAMH */
    @Operation(summary = "Get MonHoc by maMH.")
    @GetMapping("/monHoc/maMH/{maMH}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getMonHocByMaMH(@PathVariable String maMH) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MonHoc By maMH!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetMonHocByMaMH(maMH);
            MonHocEntity monHocEntity = monHocService.findByMaMH(maMH);
            returnObject.setRetObj(monHocEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY MALOP */
    @Operation(summary = "Get List Mon Hoc by maLop.")
    @GetMapping("/monHoc/lop/{maLop}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
    public ResponseEntity<?> getListMonHocByMaLop(@PathVariable String maLop) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get List Mon Hoc By maLop!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetListMonHocByMaLop(maLop);
            List<MonHocEntity> monHocEntity = monHocService.findAllByMaLop(maLop);
            returnObject.setRetObj(monHocEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* ĐK MHTQ POSSIBLE */
//    @Operation(summary = "Đang ky MHTQ.")
//    @PostMapping("/monHoc/mhtq")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
//    public ResponseEntity<?> createMonHocMHTQ(@Valid @RequestBody MHMHTQDto mhmhtqDto, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Add MHTQ for mon hoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            MHMHTQDto gvdowDtoValid = new MHMHTQDto();
//            List<String> maMHTQList = new ArrayList<>();
//
//            String maMH = mhmhtqDto.getMaMH();
//            for(String maMHTQ: mhmhtqDto.getMaMHTQList()){
//                MHTQEntity mhtqEntity = new MHTQEntity();
//                mhtqEntity.setMaMH(maMH);
//                mhtqEntity.setMaMHTQ(maMHTQ);
//
//                boolean isValid = validatorMonHoc.validateUpdateDKMHTQPossible(maMH, maMHTQ);
//                if(isValid == true){
//                    maMHTQList.add(maMHTQ);
//                }
//            }
//
//            gvdowDtoValid.setMaMH(mhmhtqDto.getMaMH());
//            gvdowDtoValid.setMaMHTQList(maMHTQList);
//
//            MHMHTQDto gvdowDtoResult = mhtqService.updateExist(gvdowDtoValid);
//
//            returnObject.setRetObj(gvdowDtoResult);
//        }
//        catch (Exception ex){
//            returnObject.setStatus(ReturnObject.ERROR);
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }


//    @Operation(summary = "Get List Mon Hoc by maLop.")
//    @GetMapping("/monHoc/lop/{maLop}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonHocEntity.class)) })})
//    public ResponseEntity<?> getListMonHocByMaLop(@PathVariable String maLop,
//                                                @RequestParam(defaultValue = "0") int page,
//                                                @RequestParam(defaultValue = "999") int size) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get List Mon Hoc By maLop!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorMonHoc.validateGetListMonHocByMaLop(maLop);
//            List<MonHocEntity> monHocEntity = monHocService.getListMonHocByMaLop(maLop, page, size);
//            returnObject.setRetObj(monHocEntity);
//
//            /*for paging*/
//            List<MonHocEntity> dsLopTcEntityForPaging = monHocService.getListMonHocByMaLop(maLop, 0, 100000);
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
////            returnObject.setMessage(ex.getMessage());
//            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
//            returnObject.setMessage(errorMessage);
//        }
//
//        return ResponseEntity.ok(returnObject);
//    }

}
