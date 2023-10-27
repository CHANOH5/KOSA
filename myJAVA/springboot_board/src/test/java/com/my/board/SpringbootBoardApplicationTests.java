package com.my.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringbootBoardApplicationTests {
//    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment env;

    @Value("${my.info.name}")
    private String name;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Environment")
    void testEnv() {
//	log.error("현재사용중인 profile={}", Arrays.toString(env.getActiveProfiles()));
//	log.error("현재 사용 중인 profile = " + Arrays.toString(env.getActiveProfiles()));
//	log.error("현재 사용 중인 my.info.deploy.msg={} ", env.getProperty("my.info.deploy.msg"));
	log.error("현재 사용 중인 my.info.dev.msg={}",
				env.getProperty("my.info.dev.msg"));
	log.error("현재 사용 중인 중복된 my.info.name={}",
				env.getProperty("my.info.name"));
	log.error("@Value(${my.info.name}", name);
	
//	env.getProperty("a", "없음");
    }

}
