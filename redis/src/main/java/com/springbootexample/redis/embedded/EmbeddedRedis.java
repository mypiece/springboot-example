package com.springbootexample.redis.embedded;

import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

@Profile({"local", "test"})
@Component
@RequiredArgsConstructor
public class EmbeddedRedis {

	private RedisServer redisServer;

	@Value("${spring.redis.port}")
	private int redisPort;

	@PostConstruct
	public void startRedis() {
		redisServer = new RedisServer(redisPort);
		redisServer.start();
	}

	@PreDestroy
	public void stopRedis() {
		Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
	}

}
