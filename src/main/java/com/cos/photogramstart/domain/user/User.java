package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website;
	private String bio;
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender; 
	
	private String profileImageUrl; //사진
	private String role; //권한
	
	private LocalDateTime creaDateTime;
	
	@PrePersist //DB에 insert되기전에 실행
	public void createDate() {
		this.creaDateTime = LocalDateTime.now(); 
	}
	
}
