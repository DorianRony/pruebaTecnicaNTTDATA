package com.ronyreyna.pruebatecnica.service;

import java.util.List;
import java.util.stream.Collectors;
import com.ronyreyna.pruebatecnica.entity.Movimiento;
import com.ronyreyna.pruebatecnica.entity.dto.ReporteRequestDTO;
import com.ronyreyna.pruebatecnica.entity.dto.ReporteResponseDTO;
import com.ronyreyna.pruebatecnica.repository.ClienteRepository;
import com.ronyreyna.pruebatecnica.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ReporteResponseDTO> generarReporte(ReporteRequestDTO reporteRequestDTO){
        List<Movimiento> movimientos = movimientoRepository.movimientosReporte(
            clienteRepository.findById(reporteRequestDTO.getIdCliente()).get(),
            reporteRequestDTO.getFechaInicio(), reporteRequestDTO.getFechaFin());
        return mappingReporteResponseDTOS(movimientos);
    }

    private List<ReporteResponseDTO> mappingReporteResponseDTOS(List<Movimiento> movimientos){
        return movimientos.stream().map(c -> ReporteResponseDTO.builder()
            .fecha(c.getFecha())
            .cliente(c.getIdCuenta().getIdCliente().getNombre())
            .numeroCuenta(c.getIdCuenta().getNumeroCuenta())
            .tipoCuenta(c.getIdCuenta().getTipoCuenta())
            .saldoInicial(c.getIdCuenta().getSaldoIncial())
            .estado(c.getIdCuenta().getEstado())
            .movimiento(c.getValor())
            .saldoDisponible(c.getSaldo())
            .build()).collect(Collectors.toList());
    }
}
