package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Document(collection = "khoa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhoaEntity {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Khoa")
	@Length(min = 2 , message = "Mã khoa chứa ít nhất 2 ký tự!")
	private String maKhoa;
	
	@NotBlank(message = "Vui Lòng Nhập Tên Khoa")
	@Length(min = 2 , message = "Mã khoa chứa ít nhất 2 ký tự!")
	private String tenKhoa;

	@Pattern(regexp = "(^$|[0-9]{10})" , message = "Phone number must be 10 digits!")
	private String sdt;
	
	@NotBlank(message = "Vui lòng nhập Email!")
	@Email(message = "Nhập đúng định dạng email!")
	private String email;
	
}
