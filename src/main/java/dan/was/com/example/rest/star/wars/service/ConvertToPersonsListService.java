package dan.was.com.example.rest.star.wars.service;


import dan.was.com.example.rest.star.wars.dto.Person;
import dan.was.com.example.rest.star.wars.dto.PersonsList;
import dan.was.com.example.rest.star.wars.dto.Planet;
import dan.was.com.example.rest.star.wars.dto.Starship;
import dan.was.com.example.rest.star.wars.responsemodel.PersonResponse;
import dan.was.com.example.rest.star.wars.responsemodel.PersonsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConvertToPersonsListService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    ConvertPersonDataService convertPersonDataService;

    private final String ALL_PEOPLE_URL = "https://swapi.dev/api/people/";

    private static int idCounter = 0;



List<Person> personList2 = new ArrayList<>();

    public PersonsListResponse convertToPersonsListResponse(String link) {
        String uri = "https://swapi.dev/api/people/?page=";

        for (int x =1; x <=2; x++) {
            String newUri = uri + x;
            System.out.println("!!!!!!!!!!!!!!!!!! "+newUri);

            PersonsList personsList = webClientBuilder.build()
                    .get()

                    .uri(newUri)
                    .retrieve()

                    .bodyToMono(PersonsList.class)
                    .block();


            List<Person> personList = Arrays.asList(personsList.getResults());
            personList2.addAll(personList);
        }
            List<PersonResponse> personResponsesList = new ArrayList<>();


            personList2.forEach(i -> {
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
//        Pageable pageable = PageRequest.of(1,9);



        PersonsListResponse personsListResponse = new PersonsListResponse(9, 86, personResponsesList);


        return personsListResponse;

    }

//    private PersonsList fetchItems(String all_people_url) {
//
//        PersonsList personsList = webClientBuilder.build()
//                .get()
//
//                .uri(ALL_PEOPLE_URL)
//                .retrieve()
//
//                .bodyToMono(PersonsList.class)
//                .block();
//
//
//        return personsList
//    }


}
