package com.springbootexample.springsecurityjwt.dao;

import static com.springbootexample.springsecurityjwt.model.entity.USER_INFO.QUserInfo.userInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootexample.springsecurityjwt.model.entity.USER_INFO.UserInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExDAO {

	private final JPAQueryFactory jPAQueryFactory;

	public Optional<UserInfo> getUserInfo(String userId){
		return Optional.ofNullable(
			jPAQueryFactory.selectFrom(userInfo)
				.where(userInfo.userId.eq(userId))
				.fetchOne()
		);
	}

}
