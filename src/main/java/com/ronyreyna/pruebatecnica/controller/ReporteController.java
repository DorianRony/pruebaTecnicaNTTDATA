package com.ronyreyna.pruebatecnica.controller;

import java.util.List;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.entity.dto.ReporteRequestDTO;
import com.ronyreyna.pruebatecnica.entity.dto.ReporteResponseDTO;
import com.ronyreyna.pruebatecnica.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping(path = "/reporteMovimientos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generar Reporte Movimientos")
    public ResponseEntity<List<ReporteResponseDTO>> guardar(@RequestBody @Validated ReporteRequestDTO reporteRequestDTO){
        return ResponseEntity.ok(reporteService.generarReporte(reporteRequestDTO));
    }
}
