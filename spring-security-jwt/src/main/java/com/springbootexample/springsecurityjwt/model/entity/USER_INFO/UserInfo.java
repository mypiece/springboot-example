package com.springbootexample.springsecurityjwt.model.entity.USER_INFO;

import com.springbootexample.springsecurityjwt.model.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USER_INFO")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
public class UserInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", nullable = false, unique = true)
	private String userId;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_ROLE", nullable = false)
	private UserRole userRole;

	@Builder
	public UserInfo(String userId, String password, UserRole userRole, String email) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	}

	public enum UserRole{
		USER, ADMIN;
	}

}
