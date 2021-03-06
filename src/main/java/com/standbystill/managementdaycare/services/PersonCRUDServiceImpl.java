package com.standbystill.managementdaycare.services;

import com.standbystill.managementdaycare.entities.Person;
import com.standbystill.managementdaycare.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PersonCRUDServiceImpl implements PersonCRUDService {
    @Autowired
    PersonRepo personRepo;

    @Override
    public int addPerson(Person person, int addressId) {
        return personRepo.addPerson(person, addressId);
    }

    @Override
    public Person findPersonById(int personId) {
        return personRepo.findPersonById(personId);
    }

    @Override
    public boolean updatePerson(Person person, int personId) {
        return personRepo.updatePerson(person,personId);
    }
}
