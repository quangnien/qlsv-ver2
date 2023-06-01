package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.entity.ChuyenNganhEntity;
import com.example.demo.service.ChuyenNganhService;
import com.example.demo.validation.ValidatorChuyenNganh;
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
@Tag(name = "ChuyenNganh", description = "Management APIs for CHUYEN NGANH.")
public class ChuyenNganhApi {

    @Autowired
    private ValidatorChuyenNganh validatorChuyenNganh;

    @Autowired
    private ChuyenNganhService chuyenNganhService;

    /* CREATE */
    @Operation(summary = "Create ChuyenNganh.")
    @PostMapping("/chuyenNganh")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) })})
    public ResponseEntity<?> createChuyenNganh(@Valid @RequestBody ChuyenNganhEntity chuyenNganhEntity, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add ChuyenNganh!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChuyenNganh.validateAddChuyenNganh(chuyenNganhEntity);

            ChuyenNganhEntity chuyenNganhEntityResult = chuyenNganhService.addChuyenNganh(chuyenNganhEntity);
            returnObject.setRetObj(chuyenNganhEntityResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET ALL */
    @Operation(summary = "Get all ChuyenNganh.")
    @GetMapping("/chuyenNganh")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) })})
    public ResponseEntity<?> getAllChuyenNganh() {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All ChuyenNganh!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<ChuyenNganhEntity> listChuyenNganhEntity = chuyenNganhService.findAllChuyenNganh();
            returnObject.setRetObj(listChuyenNganhEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get ChuyenNganh by id.")
    @GetMapping("/chuyenNganh/{chuyenNganhId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) })})
    public ResponseEntity<?> getChuyenNganhById(@PathVariable String chuyenNganhId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get ChuyenNganh By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChuyenNganh.validateGetChuyenNganhById(chuyenNganhId);

            ChuyenNganhEntity chuyenNganhEntity = chuyenNganhService.findById(chuyenNganhId);
            returnObject.setRetObj(chuyenNganhEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY MACHUYEN NGANH */
    @Operation(summary = "Get ChuyenNganh by maChuyenNganh.")
    @GetMapping("/chuyenNganh/maCN/{maCN}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChuyenNganhEntity.class)) })})
    public ResponseEntity<?> getChuyenNganhByMaChuyenNganh(@PathVariable String maCN) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get ChuyenNganh By MaChuyenNganh!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorChuyenNganh.validateGetChuyenNganhByMaChuyenNganh(maCN);

            ChuyenNganhEntity chuyenNganhEntity = chuyenNganhService.findByMaCN(maCN);
            returnObject.setRetObj(chuyenNganhEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
