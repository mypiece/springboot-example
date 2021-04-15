package com.springbootexample.redis.service;



import com.springbootexample.redis.config.CacheConfig;
import com.springbootexample.redis.model.TestVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TestService {

	@Cacheable(value = CacheConfig.USER_CACHE_KEY, key = "#key", unless = "#result == null")
	public String test(String key){

		/*
		7. 캐시를 repository에 걸어야 하냐, service에 걸어야 하냐...
		value 에 메소드 사용은 불가능한 것이 확인되었다(포기)
		그렇다면 unless 설정이 매번 중복될텐데 이걸 해결할 방법은 없을까?
		어노테이션을 확장할 경우 속성정보는 고정된다.(재정의 불가능)
		그렇다면 사용 불가능...속성정보가 매번 달라지기 때문...

		 */

		String str = String.valueOf((char)((Math.random() * 26) + 65));

		TestVO testVo = TestVO.builder().name(str).age(38).build();

		log.info("AlbumService.test(): "+ testVo.toString());

		return testVo.toString();
	}

	public String test2(){
		return test("user");
	}

}
