//package com.example.demo.api;
//
//import com.example.demo.service.*;
//import com.example.demo.validation.ValidatorLopTc;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author NienNQ
// * @created 2023 - 03 - 18 6:20 AM
// * @project qlsv
// */
//@Slf4j
//@RestController
//@RequestMapping("/api/admin")
//@Tag(name = "Danh Sach Lop Tin Chi", description = "Management APIs for DANH SACH LOP TIN CHI.")
//public class LopTcApi {
//
//    @Autowired
//    private GiangVienService giangVienService;
//
//    @Autowired
//    private MonHocService monHocService;
//
//    @Autowired
//    private LopTcService dsLopTcService;
//
//    @Autowired
//    private LopService lopService;
//
//    @Autowired
//    private ValidatorLopTc validatorLopTc;
//
//    @Autowired
//    private KeHoachNamService keHoachNamService;
//
//    @Autowired
//    private DiemService diemService;
//
//    /* CREATE */
//    @Operation(summary = "Create LopTc.")
//    @PostMapping("/dsLopTc")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> createLopTc(@Valid @RequestBody LopTcDto dsLopTc, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Add LopTc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorLopTc.validateAddLopTc(dsLopTc);
//            LopTcEntity dsLopTcEntity = (LopTcEntity) commonService.addObject(dsLopTc);
//            returnObject.setRetObj(dsLopTcEntity);
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
//
//    /* UPDATE */
//    @PutMapping("/dsLopTc")
//    @Operation(summary = "Update LopTc.")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> updateLopTc(@Valid @RequestBody LopTcDto dsLopTc, BindingResult bindingResult) {
//
//        ReturnObject returnObject = new ReturnObject();
//        if (bindingResult.hasErrors()) {
//            returnObject.setStatus(ReturnObject.ERROR);
//            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(returnObject);
//        }
//        try {
//            log.info("Update LopTc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorLopTc.validateEditLopTc(dsLopTc);
//            commonService.updateObject(dsLopTc);
//
//            returnObject.setRetObj(dsLopTc);
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
//
//    /* DELETE */
//    @DeleteMapping("/dsLopTc")
//    @Operation(summary = "Delete LopTc by list id")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> deleteLopTc(@Valid @RequestBody List<String> lstLopTcId) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Delete List LopTc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            if(lstLopTcId.size() > 0){
//                LopTcEntity dsLopTcEntity = dsLopTcService.getLopTcById(lstLopTcId.get(0));
////                validatorLopTc.validateDeleteLopTc(dsLopTcEntity);
//            }
//
//            List<String> deleteSuccess = commonService.deleteLstObject(lstLopTcId, new LopTcDto());
//            returnObject.setRetObj(deleteSuccess);
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
//
//    /* GET ALL */
//    /* GET ALL DSLOPTC BY MALOP & MAKEHOACH*/
//    /* GET ALL DSLOPTC BY MAKEHOACH*/
//    @Operation(summary = "Get all LopTc.")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @GetMapping("/dsLopTc")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getAllLopTc(@RequestParam(required = false, defaultValue = "") String maLop,
//                                           @RequestParam(required = false, defaultValue = "") String maKeHoach) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get All LopTc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            List<LopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();
//
//            List<Object> listLopTc = new ArrayList<>();
//            if(maLop.equals("") && maKeHoach.equals("")){
//                listLopTc = commonService.findAllObject(new LopTcDto());
//                returnObject.setRetObj(listLopTc);
//                return ResponseEntity.ok(returnObject);
//            }
//            else if(maLop.equals("") && !maKeHoach.equals("")){
//                List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaKeHoach(maKeHoach);
//
//                for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//                    String maGV = dsLopTcEntity.getMaGV();
//                    String maMH = dsLopTcEntity.getMaMH();
//                    String maLopNotParam = dsLopTcEntity.getMaLop();
//                    String tenGV = "";
//                    String tenMH = "";
//                    String tenLop = "";
//                    int soTc = 0;
//
//                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGV(maGV);
//                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(maMH);
//
//                    LopEntity lopEntity = lopService.getLopByMaLop(maLopNotParam);
//
//                    if(giangVienEntity != null){
//                        tenGV = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                    }
//                    if(monHocEntity != null){
//                        tenMH = monHocEntity.getTenMH();
//                        soTc = monHocEntity.getSoTc();
//                    }
//                    if(lopEntity != null){
//                        tenLop = lopEntity.getTenLop();
//                    }
//
//                    ModelMapper modelMapper = new ModelMapper();
//                    LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new LopTcMonHocGiangVienLopDto();
//                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//                    dsLopTcMonHocGiangVienLopDto.setTenGV(tenGV);
//                    dsLopTcMonHocGiangVienLopDto.setTenMH(tenMH);
//                    dsLopTcMonHocGiangVienLopDto.setTenLop(tenLop);
//                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);
//
//                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
//                }
//
////                List<LopTcEntity>  dsLopTcEntityList = dsLopTcService.findAllByMaKeHoach(maKeHoach);
////                returnObject.setRetObj(dsLopTcEntityList);
////                return ResponseEntity.ok(returnObject);
//            }
//            else if(!maLop.equals("") && maKeHoach.equals("")){
//
//                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
//                maKeHoach = keHoachNamEntity.getMaKeHoach();
//
//                List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
//                for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//                    String maGV = dsLopTcEntity.getMaGV();
//                    String maMH = dsLopTcEntity.getMaMH();
//                    String tenGV = "";
//                    String tenMH = "";
//                    int soTc = 0;
//
//                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGV(maGV);
//                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(maMH);
//
//                    if(giangVienEntity != null){
//                        tenGV = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                    }
//                    if(monHocEntity != null){
//                        soTc = monHocEntity.getSoTc();
//                        tenMH = monHocEntity.getTenMH();
//                    }
//
//                    ModelMapper modelMapper = new ModelMapper();
//                    LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new LopTcMonHocGiangVienLopDto();
//                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//                    dsLopTcMonHocGiangVienLopDto.setTenGV(tenGV);
//                    dsLopTcMonHocGiangVienLopDto.setTenMH(tenMH);
//                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);
//
//                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
//                }
//            }
//            else {
//                List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaLopAndMaKeHoach(maLop, maKeHoach);
//                for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//                    String maGV = dsLopTcEntity.getMaGV();
//                    String maMH = dsLopTcEntity.getMaMH();
//                    String tenGV = "";
//                    String tenMH = "";
//                    int soTc = 0;
//
//                    GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGV(maGV);
//                    MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(maMH);
//
//                    if(giangVienEntity != null){
//                        tenGV = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                    }
//                    if(monHocEntity != null){
//                        soTc = monHocEntity.getSoTc();
//                        tenMH = monHocEntity.getTenMH();
//                    }
//
//                    ModelMapper modelMapper = new ModelMapper();
//                    LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new LopTcMonHocGiangVienLopDto();
//                    dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//                    dsLopTcMonHocGiangVienLopDto.setTenGV(tenGV);
//                    dsLopTcMonHocGiangVienLopDto.setTenMH(tenMH);
//                    dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);
//
//                    dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
//                }
//            }
//
//            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
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
//
//    /* GET ALL DSLOPTC BY MA MON HOC & MA KE HOACH*/
//    @Operation(summary = "Get all LopTc By Ma Mon Hoc & Ma Ke Hoach")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @GetMapping("/dsLopTc/monHoc")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getAllLopTcByMaMHAndMaKeHoach(@RequestParam(required = true, defaultValue = "") String maMH,
//                                           @RequestParam(required = false, defaultValue = "") String maKeHoach) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get All LopTc By Ma Ke Hoach & Ma Mon Hoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            if(maKeHoach.equals("")){
//                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
//                maKeHoach = keHoachNamEntity.getMaKeHoach();
//            }
//
//            List<LopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();
//
//            List<Object> listLopTc = new ArrayList<>();
//
//            List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaMHAndMaKeHoach(maMH, maKeHoach);
//            for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//                String maGV = dsLopTcEntity.getMaGV();
//                String tenGV = "";
//                String tenMH = "";
//                int soTc = 0;
//
//                GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGV(maGV);
//                MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(maMH);
//
//                if(giangVienEntity != null){
//                    tenGV = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                }
//                if(monHocEntity != null){
//                    soTc = monHocEntity.getSoTc();
//                    tenMH = monHocEntity.getTenMH();
//                }
//
//                ModelMapper modelMapper = new ModelMapper();
//                LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new LopTcMonHocGiangVienLopDto();
//                dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//                dsLopTcMonHocGiangVienLopDto.setTenGV(tenGV);
//                dsLopTcMonHocGiangVienLopDto.setTenMH(tenMH);
//                dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);
//
//                dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
//            }
//
//            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
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
//
//    /* GET BY ID */
//    @Operation(summary = "Get LopTc by id.")
//    @GetMapping("/dsLopTc/{dsLopTcId}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getLopTcById(@PathVariable String dsLopTcId) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get LopTc By Id!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorLopTc.validateGetLopTcById(dsLopTcId);
//            LopTcEntity dsLopTcEntity = (LopTcEntity) commonService.getObjectById(dsLopTcId, new LopTcDto());
//            returnObject.setRetObj(dsLopTcEntity);
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
//
//    @Operation(summary = "Get danh sach lop tin chi by maLop")
//    @GetMapping("/dsLopTc/lop/{maLop}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getLopTcByMaLop(@PathVariable String maLop,
//                                               @RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "4") int size) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get Ds Lop Tin Chi By maLop!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            validatorLopTc.validateGetListLopTcByMaLop(maLop);
//            List<LopTcEntity> dsLopTcEntity = dsLopTcService.getListLopTcByMaLop(maLop, page, size);
//            returnObject.setRetObj(dsLopTcEntity);
//
//            /*for paging*/
//            List<LopTcEntity> dsLopTcEntityForPaging = dsLopTcService.getListLopTcByMaLop(maLop, 0, 100000);
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
//
//    @Operation(summary = "Get danh sach lop tin chi by maGV and maKeHoach")
//    @PostMapping("/dsLopTc/giangVien/{maGV}")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getLopTcByMaGVAndMaKeHoach(@PathVariable(required = true) String maGV,
//                                                          @RequestParam(required = false, defaultValue = "") String maKeHoach) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get Ds Lop Tin Chi By maLop!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            if(maKeHoach.equals("")){
//                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
//                maKeHoach = keHoachNamEntity.getMaKeHoach();
//            }
//
//            validatorLopTc.validateGetListLopTcByMaGVAndMaKeHoach(maGV, maKeHoach);
//
//            List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaGVAndMaKeHoach(maGV, maKeHoach);
//
//            List<LopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtoList = new ArrayList<>();
//
//            for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//
//                ModelMapper modelMapper = new ModelMapper();
//                LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//
//                MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(dsLopTcEntity.getMaMH());
//                dsLopTcMonHocGiangVienLopDto.setTenMH(monHocEntity.getTenMH());
//
//                dsLopTcMonHocGiangVienLopDtoList.add(dsLopTcMonHocGiangVienLopDto);
//            }
//
//            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtoList);
//
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
//
//    /* GET ALL DSLOPTC BY MASINH VIEN & MA KE HOACH*/
//    @Operation(summary = "Get all LopTc By Ma Mon Hoc & Ma Ke Hoach")
//    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
//    @GetMapping("/dsLopTc/sinhVien")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Success",
//                    content = {
//                            @Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "403", description = "Forbidden",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) }),
//            @ApiResponse(responseCode = "500", description = "Internal server error",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LopTcEntity.class)) })})
//    public ResponseEntity<?> getAllLopTcByMaSvAndMaKeHoach(@RequestParam(required = true, defaultValue = "") String maSv,
//                                                             @RequestParam(required = false, defaultValue = "") String maKeHoach) {
//
//        ReturnObject returnObject = new ReturnObject();
//        try {
//            log.info("Get All LopTc By Ma Sinh Vien & Ma Mon Hoc!");
//
//            returnObject.setStatus(ReturnObject.SUCCESS);
//            returnObject.setMessage("200");
//
//            if(maKeHoach.equals("")){
//                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
//                maKeHoach = keHoachNamEntity.getMaKeHoach();
//            }
//
//            List<LopTcMonHocGiangVienLopDto> dsLopTcMonHocGiangVienLopDtos = new ArrayList<>();
//
//            List<Object> listLopTc = new ArrayList<>();
//
////            List<LopTcEntity> dsLopTcEntityList = dsLopTcService.findAllByMaMHAndMaKeHoach(maMH, maKeHoach);
//            List<LopTcEntity> dsLopTcEntityList = new ArrayList<>();
//            List<DiemEntity> diemEntityList = diemService.getListDiemByMaSv(maSv);
//            for(DiemEntity diemEntity: diemEntityList){
//                LopTcEntity dsLopTcEntity = dsLopTcService.getLopTcByMaLopTcAndMaKeHoach(diemEntity.getMaLopTc(), maKeHoach);
//                if(dsLopTcEntity != null){
//                    dsLopTcEntityList.add(dsLopTcEntity);
//                }
//            }
//
//            for (LopTcEntity dsLopTcEntity: dsLopTcEntityList) {
//                String maGV = dsLopTcEntity.getMaGV();
//                String maMH = dsLopTcEntity.getMaMH();
//                String tenGV = "";
//                String tenMH = "";
//                int soTc = 0;
//
//                GiangVienEntity giangVienEntity = giangVienService.getGiangVienByMaGV(maGV);
//                MonHocEntity monHocEntity = monHocService.getMonHocByMaMH(maMH);
//
//                if(giangVienEntity != null){
//                    tenGV = giangVienEntity.getHo() + " " + giangVienEntity.getTen();
//                }
//                if(monHocEntity != null){
//                    soTc = monHocEntity.getSoTc();
//                    tenMH = monHocEntity.getTenMH();
//                }
//
//                ModelMapper modelMapper = new ModelMapper();
//                LopTcMonHocGiangVienLopDto dsLopTcMonHocGiangVienLopDto = new LopTcMonHocGiangVienLopDto();
//                dsLopTcMonHocGiangVienLopDto = modelMapper.map(dsLopTcEntity, LopTcMonHocGiangVienLopDto.class);
//                dsLopTcMonHocGiangVienLopDto.setTenGV(tenGV);
//                dsLopTcMonHocGiangVienLopDto.setTenMH(tenMH);
//                dsLopTcMonHocGiangVienLopDto.setSoTc(soTc);
//
//                dsLopTcMonHocGiangVienLopDtos.add(dsLopTcMonHocGiangVienLopDto);
//            }
//
//            returnObject.setRetObj(dsLopTcMonHocGiangVienLopDtos);
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
//
//}
