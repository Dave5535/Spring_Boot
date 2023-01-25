package com.example.spring_boot.Dao;

import com.example.spring_boot.Entity.Address;
import com.example.spring_boot.Exception.DataNotFound;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
@Repository
public class AddressImpl implements AddressDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Address persist(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findById(Integer id) {
        return  Optional.ofNullable(entityManager.find(Address.class,id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Address> findAll() {
        return entityManager.createQuery("select s from Address s ",Address.class).getResultList();
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return entityManager.merge(address);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFound {
Address address = entityManager.find(Address.class,id);
if (address != null ) entityManager.remove(address);
else throw new DataNotFound("Address whit this id dose not exists");
    }
}
