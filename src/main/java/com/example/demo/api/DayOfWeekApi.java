package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.entity.DayOfWeekEntity;
import com.example.demo.entity.MonHocEntity;
import com.example.demo.service.DayOfWeekService;
import com.example.demo.validation.ValidatorDayOfWeek;
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
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 18 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "DayOfWeek", description = "Management APIs for LOP.")
public class DayOfWeekApi {

    @Autowired
    private ValidatorDayOfWeek validatorDayOfWeek;

    @Autowired
    private DayOfWeekService dayOfWeekService;

    /* CREATE */
    @Operation(summary = "Create DayOfWeek.")
    @PostMapping("/dayOfWeek")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) })})
    public ResponseEntity<?> createDayOfWeek(@Valid @RequestBody DayOfWeekEntity dayOfWeekEntity, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add DayOfWeek!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDayOfWeek.validateAddDayOfWeek(dayOfWeekEntity);

            DayOfWeekEntity dayOfWeekEntityResult = dayOfWeekService.addNew(dayOfWeekEntity);
            returnObject.setRetObj(dayOfWeekEntityResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all DayOfWeek.")
    @GetMapping("/dayOfWeek")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) })})
    public ResponseEntity<?> getAllDayOfWeek() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All DayOfWeek!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<DayOfWeekEntity> listDayOfWeekEntity = dayOfWeekService.findAll();
            returnObject.setRetObj(listDayOfWeekEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get DayOfWeek by id.")
    @GetMapping("/dayOfWeek/{dayOfWeekId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) })})
    public ResponseEntity<?> getDayOfWeekById(@PathVariable String dayOfWeekId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get DayOfWeek By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDayOfWeek.validateGetDayOfWeekById(dayOfWeekId);

            DayOfWeekEntity dayOfWeekEntity = dayOfWeekService.findById(dayOfWeekId);
            returnObject.setRetObj(dayOfWeekEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY MADOW */
    @Operation(summary = "Get DayOfWeek by maDayOfWeek.")
    @GetMapping("/dayOfWeek/maDOW/{maDOW}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DayOfWeekEntity.class)) })})
    public ResponseEntity<?> getDayOfWeekByMaDayOfWeek(@PathVariable String maDOW) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get DayOfWeek By MaDayOfWeek!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDayOfWeek.validateGetDayOfWeekByMaDOW(maDOW);

            DayOfWeekEntity dayOfWeekEntity = dayOfWeekService.findByMaDOW(maDOW);
            returnObject.setRetObj(dayOfWeekEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    @Operation(summary = "Get List Thoi Gian Day by maGV.")
    @GetMapping("/dayOfWeek/giangVien/{maGV}")
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
    public ResponseEntity<?> getListDOWByMaGV(@PathVariable String maGV) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get List Mon Hoc By maGV!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDayOfWeek.validateGetListDOWByMaGV(maGV);
            List<DayOfWeekEntity> dayOfWeekEntityList = dayOfWeekService.findAllByMaGV(maGV);
            returnObject.setRetObj(dayOfWeekEntityList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }
}