package com.standbystill.managementdaycare.services;

import com.standbystill.managementdaycare.entities.Address;
import com.standbystill.managementdaycare.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressCRUDServiceImpl implements AddressCRUDService {
    @Autowired
    AddressRepo addressRepo;

    @Override
    public int addAddress(Address address) {
        return addressRepo.addAddress(address);
    }

    @Override
    public Address findAddressById(int id) {
        return addressRepo.findAddressById(id);
    }

    @Override
    public boolean updateAddress(Address address, int id) {
        return addressRepo.updateAddress(address,id);
    }
}
