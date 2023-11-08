package egovframework.msa.sample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
@ComponentScan("egovframework.*")
@SpringBootApplication
public class CatalogsApplication {
	
	@Bean
	public RestTemplate restTemplate() {
		
		// RestTemplate 라이브러리로 컨트롤러에서 호출될 서비스,Impl
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogsApplication.class, args);
	}

}
