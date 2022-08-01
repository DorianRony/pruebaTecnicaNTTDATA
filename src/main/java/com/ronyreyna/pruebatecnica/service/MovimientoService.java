package com.ronyreyna.pruebatecnica.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.ronyreyna.pruebatecnica.Utils;
import com.ronyreyna.pruebatecnica.entity.Cliente;
import com.ronyreyna.pruebatecnica.entity.Cuenta;
import com.ronyreyna.pruebatecnica.entity.Movimiento;
import com.ronyreyna.pruebatecnica.entity.dto.MovimientoDTO;
import com.ronyreyna.pruebatecnica.repository.MovimientoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService {

    public static final String DEBITO = "Débito";
    public static final String CREDITO = "Crédito";
    @Autowired
    private MovimientoRepository movimientoRepository;

    public MovimientoDTO guardarMovimiento(MovimientoDTO movimientoDTO) {
        if (validarMovimiento(movimientoDTO)) {
            Movimiento movimiento = Utils.mapperMovimiento(movimientoDTO);
            registrarMovimiento(movimientoDTO, movimiento.getIdCuenta());
            if (StringUtils.isBlank(movimientoDTO.getObservacion())) {
                movimiento.setSaldo(movimientoDTO.getSaldo());
                return Utils.mapperMovimientoDto(movimientoRepository.save(movimiento));
            } else {
                return movimientoDTO;
            }
        }else{
            return movimientoDTO;
        }
    }

    private boolean validarMovimiento(MovimientoDTO movimientoDTO) {
        if (movimientoDTO.getValor().doubleValue() != 0) {

            if (movimientoDTO.getTipoMovimiento().equals(DEBITO) && movimientoDTO.getValor().signum() == -1
                || movimientoDTO.getTipoMovimiento().equals(CREDITO) && movimientoDTO.getValor().signum() == 1)
            {
                return true;
            }else{
                movimientoDTO.setObservacion(
                    "El tipo de movimiento no coincide con el valor enviado: "
                        + movimientoDTO.getValor());
                return false;
            }
        } else {
            movimientoDTO.setObservacion("El valor del movimiento no puede ser 0");
            return false;
        }
    }

    private void registrarMovimiento(MovimientoDTO movimientoDTO, Cuenta cuenta) {
        List<Movimiento> movimientos = movimientoRepository.movimientosPorCuenta(cuenta);

        Movimiento ultimoMovimiento = movimientos.stream()
            .max(Comparator.comparing(Movimiento::getIdMovimiento)).orElse(null);

        if (Objects.nonNull(ultimoMovimiento)) {
            BigDecimal saldoCuenta = ultimoMovimiento.getSaldo().add(movimientoDTO.getValor());
            if (saldoCuenta.doubleValue() < 0) {
                movimientoDTO.setObservacion(
                    "Saldo no disponible");
            } else {
                movimientoDTO.setSaldo(saldoCuenta);
            }
        } else {
            BigDecimal saldoCuenta = cuenta.getSaldoIncial().add(movimientoDTO.getValor());
            if (saldoCuenta.doubleValue() < 0) {
                movimientoDTO.setObservacion(
                    "Saldo no disponible");
            } else {
                movimientoDTO.setSaldo(cuenta.getSaldoIncial().add(movimientoDTO.getValor()));
            }
        }
    }

    public void eliminarMovimiento(Integer idMovimiento) {
        movimientoRepository.deleteById(idMovimiento);
    }

    public List<MovimientoDTO> listarMovimientos() {
        return StreamSupport.stream(movimientoRepository.findAll().spliterator(), false)
            .map(Utils::mapperMovimientoDto).collect(
                Collectors.toList());
    }

    public MovimientoDTO buscarMovimiento(Integer idMovimiento) {
        Movimiento movimiento = movimientoRepository.findById(idMovimiento).orElse(null);
        if (Objects.isNull(movimiento)) {
            return null;
        } else {
            return Utils.mapperMovimientoDto(movimientoRepository.findById(idMovimiento).get());
        }
    }
}