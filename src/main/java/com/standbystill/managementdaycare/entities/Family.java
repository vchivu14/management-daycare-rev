package com.standbystill.managementdaycare.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "family", schema = "daycare12")
public class Family implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "Registration")
    private Date registration;
    @Column(name = "Phone")
    private long phone;

    @OneToMany(mappedBy = "familyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Parent> parents;

    @OneToMany(mappedBy = "familyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Child> children;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fee_id", insertable=false, updatable=false)
    private Fee fee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address_id", insertable=false, updatable=false)
    private Address address;

    public Family() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Collection<Parent> getParents() {
        return parents;
    }

    public void setParents(Collection<Parent> parents) {
        this.parents = parents;
    }

    public Collection<Child> getChildren() {
        return children;
    }

    public void setChildren(Collection<Child> children) {
        this.children = children;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registration=" + registration +
                ", phone=" + phone +
                ", fee=" + fee +
                ", address=" + address +
                '}';
    }
}
