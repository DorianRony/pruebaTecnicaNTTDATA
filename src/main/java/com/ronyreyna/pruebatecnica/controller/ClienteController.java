package com.ronyreyna.pruebatecnica.controller;

import java.util.List;
import java.util.Objects;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.exception.ClienteException;
import com.ronyreyna.pruebatecnica.service.ClienteService;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Guardar Nuevo CLiente")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody @Validated ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.guardarActualizarCliente(clienteDTO));
    }

    @PutMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar CLiente")
    public ResponseEntity<ClienteDTO> actualizar(@RequestBody @Validated ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.guardarActualizarCliente(clienteDTO));
    }

    @DeleteMapping(path = "/borrar/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Borrar Cliente")
    public ResponseEntity<?> eliminar(@PathVariable("idCliente") Integer idCliente){
        clienteService.eliminarCliente(idCliente);
        return ResponseEntity.ok().body("Cliente eliminado con exito");
    }

    @GetMapping(path = "/buscarCliente/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "buscar Cliente")
    public ResponseEntity<?> buscarCliente(@PathVariable("idCliente") Integer idCliente){
        ClienteDTO clienteDTO = clienteService.buscarCliente(idCliente);
        if(Objects.isNull(clienteDTO)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
        }else{
            return ResponseEntity.ok(clienteDTO);
        }
    }

    @GetMapping(path = "/listarClientes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "listar Clientes")
    public ResponseEntity<?> listarCliente(){
        List<ClienteDTO> clienteDTOS = clienteService.listarClientes();
        if(CollectionUtils.isEmpty(clienteDTOS)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen clientes");
        }else{
            return ResponseEntity.ok(clienteDTOS);
        }
    }
}
