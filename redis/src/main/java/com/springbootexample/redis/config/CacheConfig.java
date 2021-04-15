package com.springbootexample.redis.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching //이거 없어도 @Cacheable 자체는 사용할 수 있는데 이걸 enable해야 실제 캐싱된다.
public class CacheConfig {

	/*
	 아래 상수값은 yml 파일로 관리하고 싶었지만 코드상 상수로 정의한 이유는 다음과 같다.
	 캐시키 정보는 @Cacheable 선언시 value 에서 참조해야 하는데
	 @Cacheable value 에는 외부 메소드 사용이 불가능하고 상수값만 정의할 수 있다.
	 이에 static final 상를 사용해야 하는데 @ConfigurationProperties, @Value 어노테이션은
	 static final 변수에는 적용되지 않는다.
	 */
	public static final String CACHE_NAMESPACE = "mfk";
	public static final int DEFAULT_CACHE_TTL = 300;

	public static final String USER_CACHE_KEY = "user";
	public static final int USER_CACHE_TTL = 60;

	public static final String CONTENTS_CACHE_KEY = "contents";
	public static final int CONTENTS_CACHE_TTL = 120;

	public static final String STATS_CACHE_KEY = "stats";
	public static final int STATS_CACHE_TTL = 180;

//	private String cacheNamespace;
//	private int defaultCacheTtl;
//
//	private String userCacheKey;
//	private int userCacheTtl;
//
//	private String contentsCacheKey;
//	private int contentsCacheTtl;
//
//	private String statsCacheKey;
//	private int statsCacheTtl;

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

		//캐시 전역설정
		RedisCacheConfiguration commonConfig = RedisCacheConfiguration.defaultCacheConfig()
			.disableCachingNullValues() // null value 캐시안함
			.entryTtl(Duration.ofSeconds(DEFAULT_CACHE_TTL)) // 캐시의 기본 유효시간 설정
			.computePrefixWith(CacheKeyPrefix.prefixed(CACHE_NAMESPACE+CacheKeyPrefix.SEPARATOR))
//			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //defaultCacheConfig() 에서 해주니 안해도 됨...
			.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		//캐시 개별설정
		Map<String, RedisCacheConfiguration> individualConfig = new HashMap<>();
		individualConfig.put(USER_CACHE_KEY,
			commonConfig.entryTtl(Duration.ofSeconds(USER_CACHE_TTL)));
		individualConfig.put(CONTENTS_CACHE_KEY,
			commonConfig.entryTtl(Duration.ofSeconds(CONTENTS_CACHE_TTL)));
		individualConfig.put(STATS_CACHE_KEY,
			commonConfig.entryTtl(Duration.ofSeconds(STATS_CACHE_TTL)));

//		//캐시 전역설정
//		RedisCacheConfiguration commonConfig = RedisCacheConfiguration.defaultCacheConfig()
//			.disableCachingNullValues() // null value 캐시안함
//			.entryTtl(Duration.ofSeconds(defaultCacheTtl)) // 캐시의 기본 유효시간 설정
//			.computePrefixWith(CacheKeyPrefix.prefixed(cacheNamespace+CacheKeyPrefix.SEPARATOR))
////			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //defaultCacheConfig() 에서 해주니 안해도 됨...
//			.serializeValuesWith(RedisSerializationContext.SerializationPair
//				.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//		//캐시 개별설정
//		Map<String, RedisCacheConfiguration> individualConfig = new HashMap<>();
//		individualConfig.put(userCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(userCacheTtl)));
//		individualConfig.put(contentsCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(contentsCacheTtl)));
//		individualConfig.put(statsCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(statsCacheTtl)));
//		RedisCacheConfiguration commonConfig = RedisCacheConfiguration.defaultCacheConfig()
//			.disableCachingNullValues() // null value 캐시안함
//			.entryTtl(Duration.ofSeconds(defaultCacheTtl)) // 캐시의 기본 유효시간 설정
//			.computePrefixWith(CacheKeyPrefix.prefixed(cacheNamespace+CacheKeyPrefix.SEPARATOR))
////			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //defaultCacheConfig() 에서 해주니 안해도 됨...
//			.serializeValuesWith(RedisSerializationContext.SerializationPair
//				.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//		//캐시 개별설정
//		Map<String, RedisCacheConfiguration> individualConfig = new HashMap<>();
//		individualConfig.put(userCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(userCacheTtl)));
//		individualConfig.put(contentsCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(contentsCacheTtl)));
//		individualConfig.put(statsCacheKey,
//			commonConfig.entryTtl(Duration.ofSeconds(statsCacheTtl)));

		return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory)
			.cacheDefaults(commonConfig)
			.withInitialCacheConfigurations(individualConfig)
			.build();
	}

}
