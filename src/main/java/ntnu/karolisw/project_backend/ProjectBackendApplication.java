package ntnu.karolisw.project_backend;

import com.google.common.base.Predicates;
import org.hibernate.FlushMode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class ProjectBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create users

	}
	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build()
				.apiInfo(apiDetails());
	}
	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Qs backend API",
				"Swagger API for the backend of fullstack project, Qs",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("GitHub repo","https://github.com/eirinsvi/qs","thadshap@stud.ntnu.no"),
				"API License",
				"https://github.com/eirinsvi/qs",
				Collections.emptyList());
	}
}
