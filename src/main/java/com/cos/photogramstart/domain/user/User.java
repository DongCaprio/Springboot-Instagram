package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //Image안의 변수명 user을 써준다 (Image객체안의 user라는 이름과 연결되있어요~~)
	// -> 나는 연관관계의 주인이 아니다 그러므로 테이블에 컬럼을 만들지마!(연관관계의 주인은 image테이블의 user다)
	//user를 select할때 해당 user id로 등록된 image들을 다 가져와 
	@JsonIgnoreProperties({"user"})
	private List<Image> images;
	
	private LocalDateTime createDate;
	
	@PrePersist //DB에 insert되기전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now(); 
	}
	
}
