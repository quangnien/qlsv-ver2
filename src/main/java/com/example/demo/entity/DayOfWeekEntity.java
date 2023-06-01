package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "day_of_week")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOfWeekEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String maDOW;

	private String weekday;
	private String period;

}
