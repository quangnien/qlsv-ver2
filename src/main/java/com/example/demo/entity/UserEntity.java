package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  private String roleName;

  private String userId;
  private String userFullName;

  @DBRef
  private Set<RoleEntity> roles = new HashSet<>();

  public UserEntity(String username, String email, String password, String userId, String userFullName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.userId = userId;
    this.userFullName = userFullName;
  }

  public UserEntity(String username, String email, String password, String userId) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.userId = userId;
  }
}
