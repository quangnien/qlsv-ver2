package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonHocModifyDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chứa ít nhất 2 ký tự!")
	private String maMH;

	@NotBlank(message = "Vui Lòng Nhập Tên Môn Học")
	@Length(min = 2 , message = "Tên môn học chứa ít nhất 2 ký tự!")
	private String tenMH;

	private List<String> maMHTQList;
	private List<String> tenMHTQList;

}
