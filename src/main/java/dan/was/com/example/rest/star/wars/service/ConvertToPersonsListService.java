package dan.was.com.example.rest.star.wars.service;


import dan.was.com.example.rest.star.wars.dto.Person;
import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConvertToPersonsListService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    ConvertPersonDataService convertPersonDataService;

    private final String ALL_PEOPLE_URL = "https://swapi.dev/api/people/";

    private static int idCounter = 0;


    public PersonsListResponse convertToPersonsListResponse(String uri) {


        PersonsList personsList = webClientBuilder.build()
                .get()

                .uri(uri)
                .retrieve()

                .bodyToMono(PersonsList.class)
                .block();

        List<Person> personList = Arrays.asList(personsList.getResults());
        List<PersonResponse> personResponsesList = new ArrayList<>();


        personList.forEach(i -> {
            String homeworld = i.getHomeworld();
            List<String> starships = Arrays.asList(i.getStarships());

            Planet homeworld1 = convertPersonDataService.getHomeworld(homeworld);

            List<Starship> charactersStarships = convertPersonDataService.getCharactersStarships(starships);

            idCounter = idCounter + 1;
            PersonResponse personResponse = new PersonResponse(idCounter, i.getName(),
                    i.getBirth_year(),
                    i.getEye_color(),
                    i.getGender(),
                    i.getHair_color(),
                    i.getHeight(),
                    i.getMass(),
                    i.getSkin_color(),
                    homeworld1, charactersStarships);

            personResponsesList.add(personResponse);

        });

        personResponsesList.forEach(i -> System.out.println("Z listy" + i.toString()));

        PersonsListResponse personsListResponse = new PersonsListResponse(9, 86, personResponsesList);


        return personsListResponse;

    }


}
