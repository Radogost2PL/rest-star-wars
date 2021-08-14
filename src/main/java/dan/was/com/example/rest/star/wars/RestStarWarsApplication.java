package dan.was.com.example.rest.star.wars;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class RestStarWarsApplication {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    private static Logger LOGGER = LogManager.getLogger(RestStarWarsApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(RestStarWarsApplication.class, args);
    }

}
