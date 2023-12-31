package egovframework.msa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("egovframework.*")
@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
public class CatalogsApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
//		String profile = System.getProperty("spring.profiles.active");
//		if (profile == null) {
//			System.setProperty("spring.profiles.active", "dev");
//		}

		SpringApplication.run(CatalogsApplication.class, args);
	}

}
