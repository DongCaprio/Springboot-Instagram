package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "subscribe_uk", columnNames = {"fromUserId", "toUserId"}))
public class Subscribe {
	// N : N 의 중간테이블
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser; // 구독하는애

	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // 구독받는애

	private LocalDateTime createDate;

	@PrePersist // DB에 insert되기전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
