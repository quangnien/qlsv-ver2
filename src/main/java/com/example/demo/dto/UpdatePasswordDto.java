package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordDto {

	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mật Khẩu Cũ")
	private String matKhauCu;

	@NotBlank(message = "Vui Lòng Nhập Mật Khẩu Mới")
	@Length(min = 3 , message = "Mật khẩu chứa ít nhất 3 ký tự!")
	private String matKhauMoi;

//	@NotBlank(message = "Vui Lòng Nhập Xác Thực Mật Khẩu")
//	@Length(min = 3 , message = "Xác thực mật khẩu chứa ít nhất 3 ký tự!")
//	private String confirmPassword;

}
