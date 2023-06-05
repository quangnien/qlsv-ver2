package com.example.demo.api;

import com.example.demo.common.ReturnObject;
import com.example.demo.entity.CTDTEntity;
import com.example.demo.entity.ChuyenNganhEntity;
import com.example.demo.entity.TichLuyEntity;
import com.example.demo.service.CTDTService;
import com.example.demo.service.ChuyenNganhService;
import com.example.demo.service.TichLuyService;
import com.example.demo.validation.ValidatorChuyenNganh;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author NienNQ
 * @created 2023 - 03 - 18 6:20 AM
 * @project qlsv
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
@Tag(name = "TichLuy", description = "Management APIs for TICH LUY.")
public class TichLuyApi {

    @Autowired
    private ValidatorChuyenNganh validatorChuyenNganh;

    @Autowired
    private ChuyenNganhService chuyenNganhService;

    @Autowired
    private TichLuyService tichLuyService;

    @Autowired
    private CTDTService ctdtService;

    /* GET ALL */
    @Operation(summary = "Export Excel.")
    @GetMapping("/tichLuy/export")
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
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        // Tạo một workbook mới
        Workbook workbook = new XSSFWorkbook();

        List<CTDTEntity> ctdtEntityList = ctdtService.findAll();
        for (CTDTEntity ctdtEntity : ctdtEntityList) {

            // Tạo một sheet trong workbook
            Sheet sheet = workbook.createSheet(ctdtEntity.getTenCN() + " - " + ctdtEntity.getMaCTDT());

            List<TichLuyEntity> tichLuyEntityList = tichLuyService.findAllByMaCTDT(ctdtEntity.getMaCTDT());

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
            headerCell0.setCellValue("Mã CTDT");
            headerCell0.setCellStyle(cellStyle);

            Cell headerCell1 = headerRow.createCell(1);
            headerCell1.setCellValue("Mã Môn Học");
            headerCell1.setCellStyle(cellStyle);

            Cell headerCell2 = headerRow.createCell(2);
            headerCell2.setCellValue("Tên Môn Học");
            headerCell2.setCellStyle(cellStyle);

            Cell headerCell3 = headerRow.createCell(3);
            headerCell3.setCellValue("STC");
            headerCell3.setCellStyle(cellStyle);

            Cell headerCell4 = headerRow.createCell(4);
            headerCell4.setCellValue("Số Tiết Lý Thuyết");
            headerCell4.setCellStyle(cellStyle);

            Cell headerCell5 = headerRow.createCell(5);
            headerCell5.setCellValue("Số Tiết Thực Hành");
            headerCell5.setCellStyle(cellStyle);

            Cell headerCell6 = headerRow.createCell(6);
            headerCell6.setCellValue("TLCC");
            headerCell6.setCellStyle(cellStyle);

            Cell headerCell7 = headerRow.createCell(7);
            headerCell7.setCellValue("TLGK");
            headerCell7.setCellStyle(cellStyle);

            Cell headerCell8 = headerRow.createCell(8);
            headerCell8.setCellValue("TLCK");
            headerCell8.setCellStyle(cellStyle);

            int countRow = 2;
            for (TichLuyEntity itemTicLuy : tichLuyEntityList) {
                Row rowContent = sheet.createRow(countRow);
                rowContent.createCell(0).setCellValue(itemTicLuy.getMaCTDT());
                rowContent.createCell(1).setCellValue(itemTicLuy.getMaMH());
                rowContent.createCell(2).setCellValue(itemTicLuy.getTenMH());
                rowContent.createCell(3).setCellValue(itemTicLuy.getStc());
                rowContent.createCell(4).setCellValue(itemTicLuy.getSoTietLt());
                rowContent.createCell(5).setCellValue(itemTicLuy.getSoTietTh());
                rowContent.createCell(6).setCellValue(itemTicLuy.getTlCc());
                rowContent.createCell(7).setCellValue(itemTicLuy.getTlGk());
                rowContent.createCell(8).setCellValue(itemTicLuy.getTlCk());

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
        headers.setContentDispositionFormData("attachment", "tichluy.xlsx");

        // Trả về tệp Excel dưới dạng phản hồi HTTP để trực tiếp tải xuống
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelContent);
    }

    /* GET ALL */
    @Operation(summary = "Upload Excel.")
    @PostMapping("/tichLuy/upload")
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
    public ResponseEntity<?> processExcelUpload(@RequestParam("file") MultipartFile file) {

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

                // Lặp qua các sheet trong workbook
                for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

                    Sheet sheet = workbook.getSheetAt(sheetIndex);

                    List<TichLuyEntity> tichLuyEntityList = new ArrayList<>();

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

                            String maCTDT = cell0.getStringCellValue();
                            String maMH = cell1.getStringCellValue();
                            String tenMH = cell2.getStringCellValue();
                            int stc = (int) cell3.getNumericCellValue();
                            int soTietLT = (int) cell4.getNumericCellValue();
                            int soTietTH = (int) cell5.getNumericCellValue();
                            int tlcc = (int) cell6.getNumericCellValue();
                            int tlgk = (int) cell7.getNumericCellValue();
                            int tlck = (int) cell8.getNumericCellValue();

                            TichLuyEntity tichLuyEntity = new TichLuyEntity();
                            tichLuyEntity.setMaCTDT(maCTDT);
                            tichLuyEntity.setMaMH(maMH);
                            tichLuyEntity.setTenMH(tenMH);
                            tichLuyEntity.setStc(stc);
                            tichLuyEntity.setSoTietLt(soTietLT);
                            tichLuyEntity.setSoTietTh(soTietTH);
                            tichLuyEntity.setTlCc(tlcc);
                            tichLuyEntity.setTlGk(tlgk);
                            tichLuyEntity.setTlCk(tlck);

                            tichLuyEntityList.add(tichLuyEntity);

                        } else {
                            // Dừng lại khi gặp dòng không có dữ liệu
                            break;
                        }
                    }

                    tichLuyService.updateExist(tichLuyEntityList, sheet.getSheetName());
                }
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


    /* GET ALL */
    @Operation(summary = "Get all ChuyenNganh.")
    @GetMapping("/tichLuy")
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

            List<ChuyenNganhEntity> listChuyenNganhEntity = chuyenNganhService.findAll();
            returnObject.setRetObj(listChuyenNganhEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            String errorMessage = ex.getMessage().replace("For input string:", "").replace("\"", "");
            returnObject.setMessage(errorMessage);
        }

        return ResponseEntity.ok(returnObject);
    }

}
