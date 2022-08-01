package com.ronyreyna.pruebatecnica.controller;

import java.util.List;
import java.util.Objects;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.entity.dto.MovimientoDTO;
import com.ronyreyna.pruebatecnica.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping(path = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Guardar Nuevo Movimiento")
    public ResponseEntity<?> guardar(@RequestBody @Validated MovimientoDTO movimientoDTO){
        if(Objects.isNull(movimientoDTO.getIdMovimiento())){
            movimientoDTO = movimientoService.guardarMovimiento(movimientoDTO);
            if(StringUtils.isBlank(movimientoDTO.getObservacion())){
                return ResponseEntity.ok(movimientoDTO);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(movimientoDTO.getObservacion());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se puede actualizar registros de movimientos de la cuenta");
        }
    }

    /*@PutMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar Movimiento")
    public ResponseEntity<MovimientoDTO> actualizar(@RequestBody @Validated MovimientoDTO movimientoDTO){
        return ResponseEntity.ok(movimientoService.guardarActualizarMovimiento(movimientoDTO));
    }*/

    @DeleteMapping(path = "/borrar/{idMovimiento}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Borrar Movimiento")
    public ResponseEntity<Void> eliminar(@PathVariable("idMovimiento") Integer idMovimiento){
        movimientoService.eliminarMovimiento(idMovimiento);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/buscarMovimiento/{idMovimiento}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "buscar Movimiento")
    public ResponseEntity<?> buscarMovimiento(@PathVariable("idMovimiento") Integer idMovimiento){
        MovimientoDTO movimientoDTO = movimientoService.buscarMovimiento(idMovimiento);
        if(Objects.isNull(movimientoDTO)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El movimiento no existe");
        }else{
            return ResponseEntity.ok(movimientoDTO);
        }
    }

    @GetMapping(path = "/listarMovimientos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "listar Movimientos")
    public ResponseEntity<?> listarMovimiento(){
        List<MovimientoDTO> movimientoDTOS = movimientoService.listarMovimientos();
        if(CollectionUtils.isEmpty(movimientoDTOS)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen movimientos");
        }else{
            return ResponseEntity.ok(movimientoDTOS);
        }
    }
}
