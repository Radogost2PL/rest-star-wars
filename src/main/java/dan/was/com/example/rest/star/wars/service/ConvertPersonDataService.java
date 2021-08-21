package dan.was.com.example.rest.star.wars.service;

import dan.was.com.example.rest.star.wars.dto.Person;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("CharacterConvertService")
public class ConvertPersonDataService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    private static Logger LOGGER = LogManager.getLogger(ConvertPersonDataService.class);

    private final String ALL_PEOPLE_URL = "https://swapi.dev/api/people/";
    private final String ALL_PLANETS_URL = "https://swapi.dev/api/planets/";


    public Planet getHomeworld(String planetUri) {
        Planet planet = webClientBuilder.
                build().
                get().
                uri(planetUri).
                retrieve().
                onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Resource not found"))).
                onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new RuntimeException("Server is dead"))).
                bodyToMono(Planet.class).
                block();
        LOGGER.info("homeworld: " + planet.toString());
        return planet;
    }

    public List<Starship> getCharactersStarships(String [] starshipsUriList) {
        List<Starship> starshipsList = new ArrayList<>();

        Arrays.stream(starshipsUriList).forEach(i ->

                {
                    Starship starship = webClientBuilder.build().get().uri(i).retrieve().bodyToMono(Starship.class).block();
                    starshipsList.add(starship);
                }
        );
        LOGGER.info("Starships list: " + starshipsList);

        return starshipsList;
    }


    public PersonResponse convertPerson(String characterUri, int id) {

        Person person = webClientBuilder.
                build().
                get().
                uri(characterUri).
                retrieve().
                onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Resource not found"))).
                onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new Exception("Server is dead"))).
                bodyToMono(Person.class).block();

        String charactersHomeworldUri = person.getHomeworld();
        System.out.println(charactersHomeworldUri);

        System.out.println("!!!!!! ID Z CPDS " + person.getId());
        Planet charactersHomeworld = getHomeworld(charactersHomeworldUri);



        List<Starship> charactersStarshipsList = getCharactersStarships(person.getStarships());

        PersonResponse personResponse = new PersonResponse(id, person.getName(),
                person.getBirth_year(),
                person.getEye_color(),
                person.getGender(),
                person.getHair_color(),
                person.getHeight(),
                person.getMass(),
                person.getSkin_color(),
                charactersHomeworld, charactersStarshipsList);

        return personResponse;
    }
}
