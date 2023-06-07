package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.dto.DangKyDto;
import com.example.demo.entity.*;
import com.example.demo.service.DangKyService;
import com.example.demo.service.KeHoachNamService;
import com.example.demo.validation.ValidatorDangKy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 24 6:20 PM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Dang Ky Mon", description = "Management APIs for DANG KY MON.")
public class DangKyApi {

    @Autowired
    private DangKyService dangKyService;

    @Autowired
    private KeHoachNamService keHoachNamService;

    @Autowired
    private ValidatorDangKy validatorDangKy;

    /* CREATE - EDIT */
    @Operation(summary = "Dang ky mon.")
    @PostMapping("/dangKy")
    @PreAuthorize("hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) })})
    public ResponseEntity<?> createDangKy(@Valid @RequestBody DangKyDto dangKyMonDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Dang ky mon!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            DangKyDto dangKyMonDtoResult = dangKyService.updateExistDangKyMon(dangKyMonDto);

            returnObject.setRetObj(dangKyMonDtoResult);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID */
    @Operation(summary = "Get DangKy by id.")
    @GetMapping("/dangKy/{dangKyId}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) })})
    public ResponseEntity<?> getDangKyById(@PathVariable String dangKyId) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get DangKy By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDangKy.validateGetDangKyById(dangKyId);
            DangKyEntity dangKyEntity = dangKyService.findById(dangKyId);
            returnObject.setRetObj(dangKyEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* FIND ALL BY MALOPTC */
    @Operation(summary = "Get danh sach dangKy by maLopTc")
    @GetMapping("/dangKy/lopTc/{maLopTc}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) })})
    public ResponseEntity<?> getDsDangKyByMaLopTc(@PathVariable String maLopTc,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "999") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get danh sach dangKy By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            validatorDangKy.validateGetListDangKyByMaLopTc(maLopTc);
            List<DangKyEntity> dangKyEntity = dangKyService.findAllByMaLopTc(maLopTc, page, size);
            returnObject.setRetObj(dangKyEntity);

            /*for paging*/
            List<DangKyEntity> dangKyEntityForPaging = dangKyService.findAllByMaLopTc(maLopTc,  0, 100000);

            double totalPageDouble = (double) dangKyEntityForPaging.size() / size;
            int totalPageForPaging = (int) Math.ceil(totalPageDouble);

            returnObject.setPage(page);
            returnObject.setTotalRetObjs(dangKyEntityForPaging.size());
            returnObject.setTotalPages(totalPageForPaging);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* FIND ALL BY MASV */

    @Operation(summary = "Get danh sach dangKy sinh vien by maSinhVien & maKeHoach")
    @GetMapping("/dangKy/maSV/{maSV}")
    @PreAuthorize("hasAuthority('ROLE_GIANGVIEN') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SINHVIEN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DangKyEntity.class)) })})
    public ResponseEntity<?> getDsDangKyByMaSvAndMaKeHoach(@PathVariable(required = true) String maSV,
                                                         @RequestParam(required = false, defaultValue = "") String maKeHoach,
                                                         @RequestParam(required = false, defaultValue = "False") String mkh) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get danh sach dangKy By maLopTc!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            if(mkh.equals("False")){
                if(maKeHoach.equals("")){
                    validatorDangKy.validateGetListDangKyByMaSV(maSV);

                    List<DangKyEntity> dangKyEntityList = dangKyService.findAllByMaSV(maSV);

                    returnObject.setRetObj(dangKyEntityList);
                }
                else {
                    validatorDangKy.validateGetListDangKyByMaSVAndMaKeHoach(maSV, maKeHoach);

                    List<DangKyEntity> dangKyEntityList = dangKyService.findAllByMaSVAndMaKeHoach(maSV, maKeHoach);

                    returnObject.setRetObj(dangKyEntityList);
                }
            }
            else if(mkh.equals("True")){
                KeHoachNamEntity keHoachNamEntity = keHoachNamService.getKeHoachNamClosest();
                maKeHoach = keHoachNamEntity.getMaKeHoach();

                validatorDangKy.validateGetListDangKyByMaSVAndMaKeHoach(maSV, maKeHoach);

                List<DangKyEntity> dangKyEntityList = dangKyService.findAllByMaSVAndMaKeHoach(maSV, maKeHoach);

                returnObject.setRetObj(dangKyEntityList);
            }
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

    /* EXCEL GET BY MALOPTC */
    @Operation(summary = "Export Excel.")
    @GetMapping("/dangKy/export/lopTc/{maLopTc}")
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
    public ResponseEntity<byte[]> exportToExcelOfLopTc(@PathVariable String maLopTc) throws IOException {
        // Tạo một workbook mới
        Workbook workbook = new XSSFWorkbook();

        List<DangKyEntity> dangKyEntityList = dangKyService.findAllByMaLopTc(maLopTc);

        if(dangKyEntityList != null){
            // Tạo một sheet trong workbook
            Sheet sheet = workbook.createSheet(dangKyEntityList.get(0).getTenMH() + " - " + dangKyEntityList.get(0).getMaLopTc());

            // Tạo một đối tượng CellStyle để định dạng ô
            CellStyle cellStyle = workbook.createCellStyle();

            // Định dạng chữ in đậm
            Font font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);

            // Định dạng màu nền
            cellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Thêm dữ liệu vào sheet
            Row headerRow = sheet.createRow(1);

            Cell headerCell0 = headerRow.createCell(0);
            headerCell0.setCellValue("Mã Kế Hoạch Năm");
            headerCell0.setCellStyle(cellStyle);

            Cell headerCell1 = headerRow.createCell(1);
            headerCell1.setCellValue("Mã Lớp TC");
            headerCell1.setCellStyle(cellStyle);

            Cell headerCell2 = headerRow.createCell(2);
            headerCell2.setCellValue("Tên Môn Học");
            headerCell2.setCellStyle(cellStyle);

            Cell headerCell3 = headerRow.createCell(3);
            headerCell3.setCellValue("Mã Sinh Viên");
            headerCell3.setCellStyle(cellStyle);

            Cell headerCell4 = headerRow.createCell(4);
            headerCell4.setCellValue("Tên Sinh Viên");
            headerCell4.setCellStyle(cellStyle);

            Cell headerCell5 = headerRow.createCell(5);
            headerCell5.setCellValue("Số TC");
            headerCell5.setCellStyle(cellStyle);

            Cell headerCell6 = headerRow.createCell(6);
            headerCell6.setCellValue("Tỉ lệ CC");
            headerCell6.setCellStyle(cellStyle);

            Cell headerCell7 = headerRow.createCell(7);
            headerCell7.setCellValue("Tỉ lệ GK");
            headerCell7.setCellStyle(cellStyle);

            Cell headerCell8 = headerRow.createCell(8);
            headerCell8.setCellValue("Tỉ lệ CK");
            headerCell8.setCellStyle(cellStyle);

            Cell headerCell9 = headerRow.createCell(9);
            headerCell9.setCellValue("Điểm CC");
            headerCell9.setCellStyle(cellStyle);

            Cell headerCell10 = headerRow.createCell(10);
            headerCell10.setCellValue("Điểm GK");
            headerCell10.setCellStyle(cellStyle);

            Cell headerCell11 = headerRow.createCell(11);
            headerCell11.setCellValue("Điểm CK");
            headerCell11.setCellStyle(cellStyle);

            Cell headerCell12 = headerRow.createCell(12);
            headerCell12.setCellValue("Điểm TB");
            headerCell12.setCellStyle(cellStyle);

            Cell headerCell13 = headerRow.createCell(13);
            headerCell13.setCellValue("Xếp Loại");
            headerCell13.setCellStyle(cellStyle);

            int countRow = 2;
            for (DangKyEntity itemDangKy : dangKyEntityList) {
                Row rowContent = sheet.createRow(countRow);
                rowContent.createCell(0).setCellValue(itemDangKy.getMaKeHoach());
                rowContent.createCell(1).setCellValue(itemDangKy.getMaLopTc());
                rowContent.createCell(2).setCellValue(itemDangKy.getTenMH());
                rowContent.createCell(3).setCellValue(itemDangKy.getMaSV());
                rowContent.createCell(4).setCellValue(itemDangKy.getTenSV());
                rowContent.createCell(5).setCellValue(itemDangKy.getStc());
                rowContent.createCell(6).setCellValue(itemDangKy.getTlCc());
                rowContent.createCell(7).setCellValue(itemDangKy.getTlGk());
                rowContent.createCell(8).setCellValue(itemDangKy.getTlCk());
                rowContent.createCell(9).setCellValue(itemDangKy.getCc());
                rowContent.createCell(10).setCellValue(itemDangKy.getGk());
                rowContent.createCell(11).setCellValue(itemDangKy.getCc());
                rowContent.createCell(12).setCellValue(itemDangKy.getTb());
                rowContent.createCell(13).setCellValue(itemDangKy.getXepLoai());

                countRow++;
            }
        }

        // Ghi workbook vào ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.close();


        // Thiết lập đầu ra là một tệp Excel
        byte[] excelContent = outputStream.toByteArray();

        // Thiết lập HttpHeaders cho tệp Excel
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        headers.setContentDispositionFormData("attachment", dangKyEntityList.get(0).getMaLopTc() + ".xlsx");

        // Trả về tệp Excel dưới dạng phản hồi HTTP để trực tiếp tải xuống
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelContent);
    }

    /* EXCEL -> NHẬP ĐIỂM / EDIT ĐIỂM */
    @Operation(summary = "Upload Excel.")
    @PostMapping("/dangKy/upload/lopTc/{maLopTc}")
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
    public ResponseEntity<?> processExcelUpload(@RequestParam("file") MultipartFile file, @PathVariable String maLopTc) {

        ReturnObject returnObject = new ReturnObject();

        try {
            // Kiểm tra loại file
            if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                returnObject.setStatus(ReturnObject.ERROR);
                returnObject.setMessage("Only Excel files are allowed");
            }
            else {
                // Đọc file Excel
                Workbook workbook = new XSSFWorkbook(file.getInputStream());

                Sheet sheet = workbook.getSheetAt(0);

                List<DangKyEntity> dangKyEntityList = new ArrayList<>();

                // Bắt đầu lặp từ dòng thứ 3
                int startRow = 2; // chỉ số 2 tương đương dòng thứ 3
                int lastRowNum = sheet.getLastRowNum();
                for (int rowIndex = startRow; rowIndex <= lastRowNum; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);

                    // Kiểm tra dòng có dữ liệu hay không
                    if (row != null) {

                        Cell cell0 = row.getCell(0);
                        Cell cell1 = row.getCell(1);
                        Cell cell2 = row.getCell(2);
                        Cell cell3 = row.getCell(3);
                        Cell cell4 = row.getCell(4);
                        Cell cell5 = row.getCell(5);
                        Cell cell6 = row.getCell(6);
                        Cell cell7 = row.getCell(7);
                        Cell cell8 = row.getCell(8);
                        Cell cell9 = row.getCell(9);
                        Cell cell10 = row.getCell(10);
                        Cell cell11 = row.getCell(11);
                        Cell cell12 = row.getCell(12);
                        Cell cell13 = row.getCell(13);

                        String maKeHoach = cell0.getStringCellValue();
                        String maLopTc2 = cell1.getStringCellValue();
                        String tenMH = cell2.getStringCellValue();
                        String maSV = cell3.getStringCellValue();
                        String tenSV = cell4.getStringCellValue();
                        int soTC = (int) cell5.getNumericCellValue();
                        int tlCC = (int) cell6.getNumericCellValue();
                        int tlGK = (int) cell7.getNumericCellValue();
                        int tlCK = (int) cell8.getNumericCellValue();
                        float diemCC = (float) cell9.getNumericCellValue();
                        float diemGK = (float) cell10.getNumericCellValue();
                        float diemCK = (float) cell11.getNumericCellValue();
                        float diemTB = (float) cell12.getNumericCellValue();
                        String xepLoai = cell13.getStringCellValue();

                        DangKyEntity dangKyEntity = new DangKyEntity();
                        dangKyEntity.setMaKeHoach(maKeHoach);
                        dangKyEntity.setMaLopTc(maLopTc2);
                        dangKyEntity.setTenMH(tenMH);
                        dangKyEntity.setMaSV(maSV);
                        dangKyEntity.setTenSV(tenSV);
                        dangKyEntity.setStc(soTC);
                        dangKyEntity.setTlCc(tlCC);
                        dangKyEntity.setTlGk(tlGK);
                        dangKyEntity.setTlCk(tlCK);
                        dangKyEntity.setCc(diemCC);
                        dangKyEntity.setGk(diemGK);
                        dangKyEntity.setCk(diemCK);
                        dangKyEntity.setTb(diemTB);
                        dangKyEntity.setXepLoai(xepLoai);

                        dangKyEntityList.add(dangKyEntity);

                    } else {
                        // Dừng lại khi gặp dòng không có dữ liệu
                        break;
                    }
                }

                dangKyService.updateExistList(dangKyEntityList);
            }
        } catch (IOException e) {
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = "Failed to process Excel file";
            returnObject.setMessage(errorMessage);
        }

        returnObject.setStatus(ReturnObject.SUCCESS);
        returnObject.setMessage("200");
        return ResponseEntity.ok(returnObject);

    }
}