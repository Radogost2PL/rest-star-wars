package dan.was.com.example.rest.star.wars;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@OpenAPIDefinition
public class RestStarWarsApplication {

    private static final Logger LOGGER = LogManager.getLogger(RestStarWarsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestStarWarsApplication.class, args);
    }
}
