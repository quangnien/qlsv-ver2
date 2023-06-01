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

@Document(collection = "lich_hoc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LichHocEntity {

	@Id
	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 2 , message = "Mã lớp tín chỉ chứa ít nhất 2 ký tự!")
	private String maLopTc;

	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String tenLop;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date tgDongDk;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date tgMoDk;

	private String coVanHt;

	/* FOREIGN KEY */

	@NotBlank(message = "Vui Lòng Nhập Mã Chuyên Ngành")
	@Length(min = 2 , message = "Mã chuyên ngành chứa ít nhất 2 ký tự!")
	private String maCN;

	private String tenCN;

}
