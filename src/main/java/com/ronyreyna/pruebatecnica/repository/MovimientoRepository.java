package com.ronyreyna.pruebatecnica.repository;

import java.util.Date;
import java.util.List;
import com.ronyreyna.pruebatecnica.entity.Cliente;
import com.ronyreyna.pruebatecnica.entity.Cuenta;
import com.ronyreyna.pruebatecnica.entity.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {
    @Query("select c from movimiento c where c.idCuenta = ?1")
    List<Movimiento> movimientosPorCuenta(Cuenta cuenta);

    @Query("select c from movimiento c where c.idCuenta.idCliente = ?1 and c.fecha >= ?2 and c.fecha <= ?3")
    List<Movimiento> movimientosReporte(Cliente cliente, Date fechaInicio, Date fechaFin);
}
