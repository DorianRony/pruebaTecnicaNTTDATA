package com.ronyreyna.pruebatecnica.repository;

import com.ronyreyna.pruebatecnica.entity.Cliente;
import com.ronyreyna.pruebatecnica.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {
    @Override
    void deleteById(Integer integer);
}
