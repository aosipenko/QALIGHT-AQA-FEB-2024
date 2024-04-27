package org.web.cucumber;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.web.db.Persons;
import org.web.db.PersonsJpa;
import org.web.dto.PersonDto;
import org.web.util.DataHolder;

import static org.testng.Assert.assertNotNull;

public class DbSteps {

    @Autowired
    private DataHolder dataHolder;

    @Autowired
    private PersonsJpa personsJpa;

    @Given("I pick a random person from DB as {string}")
    public void pickRandomPerson(String alias) {
        PersonDto randomPerson = PersonDto.fromDb(personsJpa.getRandomPerson().get());
        assertNotNull(randomPerson, "Failed to extract random person from DB");

        dataHolder.put(alias,
                randomPerson.getName().getFirst() + " " + randomPerson.getName().getLast());
    }

    @Given("I print all users from DB")
    public void printAll() {
        personsJpa.findAll()
                .forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName()));
//        personsJpa.findAll(Example.of(Persons.builder().title("Mr").build()));
    }
}
