package com.springbootexample.springsecurityjwt.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springbootexample.springsecurityjwt.common.utils.LocalDateUtils;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

	@CreatedDate
	@Column(name = "REG_DT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateUtils.T_DATE_TIME_FORMAT, timezone = "Asia/Seoul")
	private LocalDateTime regDt;

	@LastModifiedDate
	@Column(name = "UPDATE_DT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LocalDateUtils.T_DATE_TIME_FORMAT, timezone = "Asia/Seoul")
	private LocalDateTime updateDt;

}
