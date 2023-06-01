package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "gv_dow")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'maGV': 1, 'maDOW': 1}", unique = true)
public class GvDowEntity {

	@Id
	private String id;

	/* FOREIGN KEY */
	@NotBlank(message = "Vui Lòng Chọn Giảng Viên")
	private String maGV;

	@NotBlank(message = "Vui Lòng Chọn Ngày Trong Tuần")
	private String maDOW;

	@JsonIgnore
	private String tenGV;

}
