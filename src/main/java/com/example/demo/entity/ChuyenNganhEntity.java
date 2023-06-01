package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "chuyen_nganh")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChuyenNganhEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Chuyên Ngành")
	@Length(min = 2, message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maCN;

	@NotBlank(message = "Vui Lòng Nhập Mã Chuyên Ngành")
	@Length(min = 2, message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String tenCN;
}