package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "mon_hoc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonHocEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chứa ít nhất 2 ký tự!")
	private String maMH;
	
	@NotBlank(message = "Vui Lòng Nhập Tên Môn Học")
	@Length(min = 2 , message = "Tên môn học chứa ít nhất 2 ký tự!")
	private String tenMH;
}
