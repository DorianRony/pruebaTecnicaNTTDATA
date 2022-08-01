package com.ronyreyna.pruebatecnica.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.ronyreyna.pruebatecnica.Utils;
import com.ronyreyna.pruebatecnica.entity.Cuenta;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.entity.dto.CuentaDTO;
import com.ronyreyna.pruebatecnica.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaDTO guardarActualizarCuenta(CuentaDTO cuentaDTO) {
        return Utils.mapperCuentaDto(
            cuentaRepository.save(Utils.mapperCuenta(cuentaDTO)));
    }

    public void eliminarCuenta(Integer idCuenta) {
        cuentaRepository.deleteById(idCuenta);
    }

    public List<CuentaDTO> listarCuentas() {
        return StreamSupport.stream(cuentaRepository.findAll().spliterator(), false)
            .map(Utils::mapperCuentaDto).collect(
                Collectors.toList());
    }

    public CuentaDTO buscarCuenta(Integer idCuenta) {
        Cuenta cuenta = cuentaRepository.findById(idCuenta).orElse(null);
        if (Objects.isNull(cuenta)) {
            return null;
        } else {
            return Utils.mapperCuentaDto(cuenta);
        }
    }
}
