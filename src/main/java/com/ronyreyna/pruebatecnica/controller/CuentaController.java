package com.ronyreyna.pruebatecnica.controller;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.ronyreyna.pruebatecnica.entity.dto.CuentaDTO;
import com.ronyreyna.pruebatecnica.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping(path = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Guardar Nuevo Cuenta")
    public ResponseEntity<CuentaDTO> guardar(@RequestBody @Validated CuentaDTO cuentaDTO){
        return ResponseEntity.ok(cuentaService.guardarActualizarCuenta(cuentaDTO));
    }

    @PutMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar Cuenta")
    public ResponseEntity<CuentaDTO> actualizar(@RequestBody @Validated CuentaDTO cuentaDTO){
        return ResponseEntity.ok(cuentaService.guardarActualizarCuenta(cuentaDTO));
    }

    @DeleteMapping(path = "/borrar/{idCuenta}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Borrar Cuenta")
    public ResponseEntity<?> eliminar(@PathVariable("idCuenta") Integer idCuenta){
        cuentaService.eliminarCuenta(idCuenta);
        return ResponseEntity.ok().body("Cuenta eliminada con exito");
    }

    @GetMapping(path = "/buscarCuenta/{idCuenta}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "buscar Cuenta")
    public ResponseEntity<?> buscarCuenta(@PathVariable("idCuenta") Integer idCuenta){
        CuentaDTO cuentaDTO = cuentaService.buscarCuenta(idCuenta);
        if(Objects.isNull(cuentaDTO)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta no existe");
        }else{
            return ResponseEntity.ok(cuentaDTO);
        }
    }

    @GetMapping(path = "/listarCuentas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "listar Cuentas")
    public ResponseEntity<?> listarCuenta(){
        List<CuentaDTO> cuentaDTOS = cuentaService.listarCuentas();
        if (CollectionUtils.isEmpty(cuentaDTOS)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen cuentas");
        }else{
            return ResponseEntity.ok(cuentaDTOS);
        }
    }
}
