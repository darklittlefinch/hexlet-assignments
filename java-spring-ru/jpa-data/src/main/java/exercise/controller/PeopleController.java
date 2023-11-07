package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Person;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Person> index(
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "10") long limit) {
        return personRepository.findAll().stream()
                .skip((page - 1) * limit)
                .limit(limit)
                .toList();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
    // END
}
