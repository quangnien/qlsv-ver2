package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "tich_luy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maMH': 1, 'maCTDT': 1}", unique = true)
public class TichLuyEntity {

	@Id
	private String id;

	private int stc;
	private int soTietLt;
	private int soTietTh;
	private int ky;
	private int tlCc;
	private int tlGk;
	private int tlCk;

	/* FOREIGN KEY */

	@NotBlank(message = "Vui Lòng Nhập Mã Lớp")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maCTDT;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học")
	@Length(min = 2 , message = "Mã môn học chứa ít nhất 2 ký tự!")
	private String maMH;

	private String tenCTDT;
	private String tenMH;
}
