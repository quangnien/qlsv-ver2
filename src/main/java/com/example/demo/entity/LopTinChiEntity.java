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
import java.util.Date;

@Document(collection = "lop_tin_chi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LopTinChiEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 2 , message = "Mã lớp tín chỉ chứa ít nhất 2 ký tự!")
	private String maLopTc;

	@NotBlank(message = "Vui Lòng Nhập Niên Khóa")
	@Length(min = 2 , message = "Niên khóa chứa ít nhất 2 ký tự!")
	private String nienKhoa;

	private int ky;
	private int nhom;
	private int soLuongThucTe;
	private int soLuongToiDa;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeBd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date timeKt;

//	@DateTimeFormat(pattern =  "yyyy-MM-dd")
//	private Date tgDongDk;
//
//	@DateTimeFormat(pattern =  "yyyy-MM-dd")
//	private Date tgMoDk;

	private int trangThai;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chỉ chứa ít nhất 2 ký tự!")
	private String maMH;

	@NotBlank(message = "Vui Lòng Nhập Mã Giáo Viên")
	@Length(min = 2 , message = "Mã giáo viên chỉ chứa ít nhất 2 ký tự!")
	private String maGV;

	@NotBlank(message = "Vui Lòng Nhập Mã Kế Hoạch")
	@Length(min = 2 , message = "Mã kế hoạch chỉ chứa ít nhất 2 ký tự!")
	private String maKeHoach;

	private String tenMH;
	private String tenGV;
	private String tenKeHoach;

}
