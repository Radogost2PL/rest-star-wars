package dan.was.com.example.rest.star.wars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Rest Star Wars")
                .description("For evaluation my coding skills only")
                .termsOfServiceUrl("https://apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Radogost2PL", "https://github.com/Radogost2PL/rest-star-wars"
                        , "d.wasil@protonmail.com")).build();
    }
}
