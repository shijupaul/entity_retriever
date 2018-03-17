package com.shijusoft.exception.service;

import com.shijusoft.exception.dto.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by spaul on 21/02/2018.
 */
@Service
public class PersonService {

    private static final String CREATE_PERSON_URL = "%s/person";

    private static final String GET_PERSON_URL = "%s/person/{personId}";

    private static final String GET_PERSON_ALL_URL = "%s/person/all";


    private RestTemplate restTemplate;

    @Value("${entity.holder.url}")
    private String entityHolderUrl;

    public PersonService() {
        restTemplate = new RestTemplate();
    }

    public Optional<Person> findMatchingPerson(int personId) {
        try {
            ResponseEntity<Person> responseEntity = restTemplate.getForEntity(String.format(GET_PERSON_URL, entityHolderUrl), Person.class, personId);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return Optional.of(responseEntity.getBody());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void add(Person person) {
        ResponseEntity<Person> responseEntity =  restTemplate.postForEntity(String.format(CREATE_PERSON_URL,entityHolderUrl), person, Person.class);
    }

//    public List<Person> getAll() {
//
//        ResponseEntity<Object>  responseEntity =  restTemplate.getForEntity(String.format(GET_PERSON_ALL_URL, entityHolderUrl), Object.class);
//        List<Person> personList = new ArrayList();
////        responseEntity.getBody().getContent().forEach(entity -> personList.add( (Person)entity));
//
//        return personList;
//    }
}
