package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "mhtq")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maMH': 1, 'maMHTQ': 1}", unique = true)
public class MHTQEntity {

	@Id
	private String id;

	@NotBlank(message = "Vui Lòng Nhập Mã Môn Học Tiên Quyết")
	@Length(min = 2 , message = "Mã môn học tiên quyết chứa ít nhất 2 ký tự!")
	private String maMHTQ;

	@NotBlank(message = "Vui Lòng Nhập Tên Môn Học Tiên Quyết")
	@Length(min = 2 , message = "Tên môn học tiên quyết chứa ít nhất 2 ký tự!")
	private String maMH;

	private String tenMHTQ;
	private String tenMH;
}
