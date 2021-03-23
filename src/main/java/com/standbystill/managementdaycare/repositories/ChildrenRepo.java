package com.standbystill.managementdaycare.repositories;

import com.standbystill.managementdaycare.entities.Child;
import com.standbystill.managementdaycare.entities.Family;

import java.util.List;

public interface ChildrenRepo {
    List<Child> fetchAll();

    List<Child> fetchChildrenForFamily(int familyId);

    Family fetchFamilyForChildren(int familyId);

    void addChild(Child child, int familyId, int personId);

    boolean updateChild(String firstName, String lastName, int age, int personId);

    boolean deleteChild(int personId);

    Child findChildById(int personId);

    List<Child> findChildrenByLastName(String lastName);
}
