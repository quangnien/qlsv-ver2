package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "diem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maSV': 1, 'maLopTc': 1}", unique = true)
public class DangKyEntity {
	
	@Id
	private String id;

	private float cc;
	private float gk;
	private float ck;
	private float tb;
	
//	@NotBlank(message = "Vui Lòng Nhập Xếp Loại")
	private String xepLoai;

	private int trangThaiDk;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Nhập Mã Sinh Viên")
	@Length(min = 2 , message = "Mã sinh viên tín chỉ chứa ít nhất 2 ký tự!")
	private String maSV;

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp Tín Chỉ")
	@Length(min = 2 , message = "Mã lớp tín chỉ chứa ít nhất 2 ký tự!")
	private String maLopTc;

	private String tenSV;
	private String tenLopTc;
	
}
