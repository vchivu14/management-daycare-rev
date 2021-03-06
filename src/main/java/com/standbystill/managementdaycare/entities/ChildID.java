package com.standbystill.managementdaycare.entities;

import java.io.Serializable;
import java.util.Objects;

public class ChildID implements Serializable {
    private int familyId;
    private int personId;

    public ChildID() {
    }

    public ChildID(int id, int familyId, int personId) {
        this.familyId = familyId;
        this.personId = personId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildID)) return false;
        ChildID childID = (ChildID) o;
        return getFamilyId() == childID.getFamilyId() && getPersonId() == childID.getPersonId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFamilyId(), getPersonId());
    }
}
