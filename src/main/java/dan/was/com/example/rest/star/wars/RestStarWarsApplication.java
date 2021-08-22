package dan.was.com.example.rest.star.wars;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestStarWarsApplication {

    private static Logger LOGGER = LogManager.getLogger(RestStarWarsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestStarWarsApplication.class, args);
    }
}
