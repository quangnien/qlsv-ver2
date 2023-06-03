package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.dto.MHMHTQDto;
import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.MHTQEntity;
import com.example.demo.entity.MHTQEntity;
import com.example.demo.service.MHTQService;
import com.example.demo.service.MHTQService;
import com.example.demo.validation.ValidatorMHTQ;
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
@Tag(name = "Mon Hoc Tien Quyet", description = "Management APIs for MHTQ.")
public class MHTQApi {

    @Autowired
    private MHTQService mhtqService;

    @Autowired
    private ValidatorMHTQ validatorMHTQ;

    @Autowired
    private ValidatorMonHoc validatorMonHoc;

    /* GET ALL */
    @Operation(summary = "Get all MHTQ.")
    @GetMapping("/mhtq")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) })})
    public ResponseEntity<?> getAllMHTQ() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All MHTQ!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<MHTQEntity> MHTQEntityList = mhtqService.findAll();
            returnObject.setRetObj(MHTQEntityList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get MHTQ by id.")
    @GetMapping("/mhtq/{mhtqId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) })})
    public ResponseEntity<?> getMHTQById(@PathVariable String mhtqId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MHTQ By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMHTQ.validateGetMHTQById(mhtqId);
            MHTQEntity MHTQEntity = mhtqService.findById(mhtqId);
            returnObject.setRetObj(MHTQEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY MAMH */
    @Operation(summary = "Get MHTQ by maMH.")
    @GetMapping("/mhtq/maMH/{maMH}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MHTQEntity.class)) })})
    public ResponseEntity<?> getMHTQByMaMH(@PathVariable String maMH) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get MHTQ By maMH!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorMonHoc.validateGetMonHocByMaMH(maMH);
            List<MHTQEntity> mhtqEntityList = mhtqService.findAllByMaMH(maMH);
            returnObject.setRetObj(mhtqEntityList);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }
}
