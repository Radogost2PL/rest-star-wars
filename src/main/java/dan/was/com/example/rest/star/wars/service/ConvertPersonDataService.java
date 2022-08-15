package dan.was.com.example.rest.star.wars.service;

import dan.was.com.example.rest.star.wars.dto.Person;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
import dan.was.com.example.rest.star.wars.exceptions.remote.ResourceNotFoundExceptions;
import dan.was.com.example.rest.star.wars.exceptions.remote.InternalErrorException;
import dan.was.com.example.rest.star.wars.responsemodel.HomeworldResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.StarshipResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("CharacterConvertService")
public class ConvertPersonDataService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final Logger LOGGER = LogManager.getLogger(ConvertPersonDataService.class);


    @Value("${rest.url.swapi}")
    private String BASE_URL;
    @Value("${rest.url.allPeople}")
    private String ALL_PEOPLE_URL;

    public HomeworldResponse getHomeworld(String planetUri) {
        return convertPlanetToHomeworldResponse(Objects.requireNonNull(webClientBuilder.
                build().
                get().
                uri(planetUri).
                retrieve().
                onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("Resource not found"))).
                onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new RuntimeException("Server is dead"))).
                bodyToMono(Planet.class).
                block()));
    }

    public HomeworldResponse convertPlanetToHomeworldResponse(Planet planet) {

        return new HomeworldResponse(planet.getName(),
                planet.getRotation_period(),
                planet.getOrbital_period(),
                planet.getDiameter(),
                planet.getClimate(),
                planet.getGravity(),
                planet.getTerrain(),
                planet.getSurface_water(),
                planet.getPopulation());
    }

    public StarshipResponse convertStarshipToStarshipResponse(Starship starship) {
        return new StarshipResponse(starship.getName(),
                starship.getModel(),
                starship.getManufacturer(),
                starship.getCost_in_credits(),
                starship.getLength(),
                starship.getMax_atmosphering_speed(),
                starship.getCrew(),
                starship.getPassengers(),
                starship.getCargo_capacity(),
                starship.getConsumables(),
                starship.getHyperdrive_rating(),
                starship.getMglt(),
                starship.getStarship_class());

    }

    public List<StarshipResponse> getCharactersStarships(String[] starshipsUriList) {
        List<StarshipResponse> starshipsResponseList = new ArrayList<>();

        Arrays.stream(starshipsUriList).forEach(i ->
                {
                    StarshipResponse starshipResponse = convertStarshipToStarshipResponse(Objects.
                            requireNonNull(webClientBuilder.
                                    build().
                                    get().
                                    uri(i).
                                    retrieve().
                                    bodyToMono(Starship.class).
                                    block()));

                    starshipsResponseList.add(starshipResponse);
                }
        );
        LOGGER.info("Starships list: " + starshipsResponseList);

        return starshipsResponseList;
    }

    public PersonResponse convertPerson(int id) throws ResourceNotFoundExceptions {

        Person person = webClientBuilder.
                build().
                get().
                uri(BASE_URL, uriBuilder -> uriBuilder.path(ALL_PEOPLE_URL).build(id)).
                retrieve().
                onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new ResourceNotFoundExceptions("Remote resource not found"))).
                onStatus(HttpStatus::is5xxServerError, error -> Mono.error(new InternalErrorException("Remote server internal error"))).
                bodyToMono(Person.class).block();

        String charactersHomeworldUri = person.getHomeworld();

        return new PersonResponse(id, person.getName(),
                person.getBirth_year(),
                person.getEye_color(),
                person.getGender(),
                person.getHair_color(),
                person.getHeight(),
                person.getMass(),
                person.getSkin_color(),
                getHomeworld(charactersHomeworldUri),
                getCharactersStarships(person.getStarships()));
    }
}
