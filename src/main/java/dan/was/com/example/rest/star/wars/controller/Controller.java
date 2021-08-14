package dan.was.com.example.rest.star.wars.controller;


import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.RestResponse;
import dan.was.com.example.rest.star.wars.service.ConvertPersonDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
public class Controller {
    private static Logger LOGGER = LogManager.getLogger(Controller.class);
    private final String ALL_PEOPLE_URL = "https://swapi.dev/api/people/";
    private final String ALL_PEOPLE_URL_PAGE = "https://swapi.dev/api/people/?page=";
    private final String ALL_PLANETS_URL = "https://swapi.dev/api/planets/";


    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private ConvertPersonDataService convertPersonData;

    @GetMapping("/characters/page={id}")
    public PersonsList getAllCharactersFromPage(@PathVariable("id") Integer id) {
        return webClientBuilder.build()
                .get()

                .uri(ALL_PEOPLE_URL_PAGE + id)
                .retrieve()

                .bodyToMono(PersonsList.class)
                .block();

    }


    @GetMapping("/characters/{id}")
    public RestResponse getCharacterById(@PathVariable("id") Integer id) {

        String character = ALL_PEOPLE_URL + id;
        PersonResponse personResponse = new PersonResponse();
        try {
            personResponse = convertPersonData.convertPerson(character, id);
        } catch (Exception e) {
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage(e.getMessage());
            return restResponse;
        }
        return personResponse;
    }

}
