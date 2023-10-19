package avlyakulov.timur.DockerTestProject.services;

import avlyakulov.timur.DockerTestProject.models.Person;
import avlyakulov.timur.DockerTestProject.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void createPerson(Person person) {

    }
}
