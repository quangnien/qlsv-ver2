package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "giang_day")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiangDayEntity {

	@Id
	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Giảng Viên")
	@Length(min = 2 , message = "Mã giảng viên chỉ chứa ít nhất 2 ký tự!")
	private String maGV;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chỉ chứa ít nhất 2 ký tự!")
	private String maMH;

	private String tenGV;
	private String tenMH;

}
