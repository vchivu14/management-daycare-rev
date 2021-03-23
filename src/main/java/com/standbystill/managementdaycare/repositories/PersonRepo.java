package com.standbystill.managementdaycare.repositories;

import com.standbystill.managementdaycare.entities.Child;
import com.standbystill.managementdaycare.entities.Person;

public interface PersonRepo {
    int addPerson(Person person, int addressId);

    Person findPersonById(int personId);
}
