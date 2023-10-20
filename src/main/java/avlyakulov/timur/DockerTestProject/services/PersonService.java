package avlyakulov.timur.DockerTestProject.services;

import avlyakulov.timur.DockerTestProject.models.Person;
import avlyakulov.timur.DockerTestProject.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    public Person getPersonById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    @Transactional
    public void updatePerson(Person updatedPerson, int id) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setUsername(updatedPerson.getUsername());
        person.setEmail(updatedPerson.getEmail());
        person.setPassword(updatedPerson.getPassword());
    }

    @Transactional
    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}
