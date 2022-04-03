package ntnu.karolisw.project_backend;

import org.hibernate.FlushMode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create users

	}
}