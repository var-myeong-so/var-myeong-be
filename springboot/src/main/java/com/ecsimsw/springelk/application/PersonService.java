package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Person;
import com.ecsimsw.springelk.domain.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void testCreate() {
        final Person code = new Person("ecsimsw", 25);
        personRepository.save(code);
    }
}
