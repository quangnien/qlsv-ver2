package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Document(collection = "ke_hoach_nam")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeHoachNamEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	@NotBlank(message = "Vui Lòng Nhập Mã Kế Hoạch")
	@Length(min = 2 , message = "Mã lớp chứa ít nhất 2 ký tự!")
	private String maKeHoach;

	private int ky;

	private int nam;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeDkMonBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeDkMonEnd;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeStudyBegin;

	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private LocalDate timeStudyEnd;

}