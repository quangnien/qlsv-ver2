package com.example.demo.entity;

import com.example.demo.entity.user.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

  @Id
  private String id;

  private ERole name;
}
