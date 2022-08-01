package com.ronyreyna.pruebatecnica.repository;

import com.ronyreyna.pruebatecnica.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    @Override
    void deleteById(Integer integer);
}
