package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Document(collection = "lop_tin_chi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LopTcEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 2 , message = "Mã lớp tín chỉ chứa ít nhất 2 ký tự!")
	private String maLopTc;

	@NotBlank(message = "Vui Lòng Nhập Niên Khóa")
	@Length(min = 2 , message = "Niên khóa chứa ít nhất 2 ký tự!")
	private String nienKhoa;

	@NotBlank(message = "Vui Lòng Nhập Số Lượng Sinh Viên")
	private int soLuong;

	private int soLuongCon;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeKt;

	private int trangThai;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chỉ chứa ít nhất 2 ký tự!")
	private String maMH;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp")
	@Length(min = 4 , message = "Mã lớp chứa ít nhất 4 ký tự!")
	private String maLop;

	@NotBlank(message = "Vui Lòng Nhập Mã Kế Hoạch")
	@Length(min = 2 , message = "Mã kế hoạch chỉ chứa ít nhất 2 ký tự!")
	private String maKeHoach;

	private String tenMH;
	private String tenLop;

}
