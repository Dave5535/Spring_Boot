package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Address;
import com.example.spring_boot.Exception.DataNotFound;

import java.util.Collection;
import java.util.Optional;

public interface AddressDao {

    Address persist(Address address);

    Optional<Address> findById(Integer id);

    Collection<Address> findAll();

    Address update(Address address);

    void remove(Integer id) throws DataNotFound;
}
