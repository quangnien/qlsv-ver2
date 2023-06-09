package com.example.demo.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Document(collection = "sinh_vien")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinhVienEntity implements UserInterface{
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Sinh Viên")
	@Length(min = 2 , message = "Mã sinh viên chứa ít nhất 2 ký tự!")
	private String maSV;
	
	@NotBlank(message = "Vui Lòng Nhập Họ")
	private String ho;
	
	@NotBlank(message = "Vui Lòng Nhập Tên")
	private String ten;

	@NotBlank(message = "Vui Lòng Nhập Địa Chỉ")
	private String diaChi;

	@NotBlank(message = "Vui Lòng Nhập Giới Tính")
	private String phai;
	
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date ngaySinh;

	@Pattern(regexp = "(^$|[0-9]{10})" , message = "Phone number must be 10 digits!")
	private String sdt;
	
	private int trangThai;

	private String hinhAnh;

	@NotBlank(message = "Vui lòng nhập Email!")
	@Email(message = "Nhập đúng định dạng email!")
	private String email;

	@JsonIgnore
	private String password;
	
	/* FOREIGN KEY */

	@NotBlank(message = "Vui Lòng Mã Lớp")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maLop;

	private String tenLop;


	/* more method */

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	@Override
	public String sayHello() {
		return "Tạo tài khoản sinh viên : " + this.ho + " " + this.ten + " thành công!";
	}
}
