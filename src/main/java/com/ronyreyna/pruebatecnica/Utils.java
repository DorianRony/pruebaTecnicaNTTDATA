package com.ronyreyna.pruebatecnica;

import java.util.Objects;
import javax.annotation.PostConstruct;
import com.ronyreyna.pruebatecnica.entity.Cliente;
import com.ronyreyna.pruebatecnica.entity.Cuenta;
import com.ronyreyna.pruebatecnica.entity.Movimiento;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.entity.dto.CuentaDTO;
import com.ronyreyna.pruebatecnica.entity.dto.MovimientoDTO;
import com.ronyreyna.pruebatecnica.repository.ClienteRepository;
import com.ronyreyna.pruebatecnica.repository.CuentaRepository;
import com.ronyreyna.pruebatecnica.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    private static ClienteRepository clienteRepositoryStatic;
    private static CuentaRepository cuentaRepositoryStatic;

    @PostConstruct
    private void initStaticRepository () {
        clienteRepositoryStatic = this.clienteRepository;
        cuentaRepositoryStatic = this.cuentaRepository;
    }

    public static ClienteDTO mapperClienteDto(Cliente cliente){
        return ClienteDTO.builder()
            .nombre(cliente.getNombre())
            .genero(cliente.getGenero())
            .edad(cliente.getEdad())
            .identificacion(cliente.getIdentificacion())
            .direccion(cliente.getDireccion())
            .telefono(cliente.getTelefono())
            .contrasena(cliente.getContrasena())
            .estado(cliente.isEstado())
            .idCliente(cliente.getIdCliente())
            .build();
    }

    public static Cliente mapperCliente(ClienteDTO clienteDTO){
        if (Objects.isNull(clienteDTO)){
            System.out.printf("Error null");
        }else{
            System.out.printf("no es null");
        }
        return new Cliente(clienteDTO.getNombre(),clienteDTO.getGenero(), clienteDTO.getEdad(),
            clienteDTO.getIdentificacion(), clienteDTO.getDireccion(),clienteDTO.getTelefono(),clienteDTO.getIdCliente(),clienteDTO.getContrasena(),clienteDTO.getEstado());
    }

    public static Cuenta mapperCuenta(CuentaDTO cuentaDTO){
        return Cuenta.builder().idCuenta(cuentaDTO.getIdCuenta())
            .numeroCuenta(cuentaDTO.getNumeroCuenta())
            .idCuenta(cuentaDTO.getIdCuenta())
            .tipoCuenta(cuentaDTO.getTipoCuenta())
            .estado(cuentaDTO.getEstado())
            .saldoIncial(cuentaDTO.getSaldoIncial())
            .idCliente(clienteRepositoryStatic.findById(cuentaDTO.getIdCliente()).get())
            .build();
    }

    public static CuentaDTO mapperCuentaDto(Cuenta cuenta){
        return CuentaDTO.builder().idCuenta(cuenta.getIdCuenta())
            .numeroCuenta(cuenta.getNumeroCuenta())
            .idCuenta(cuenta.getIdCuenta())
            .estado(cuenta.getEstado())
            .tipoCuenta(cuenta.getTipoCuenta())
            .saldoIncial(cuenta.getSaldoIncial())
            .idCliente(cuenta.getIdCliente().getIdCliente())
            .build();
    }

    public static MovimientoDTO mapperMovimientoDto(Movimiento movimiento){
        return MovimientoDTO.builder()
            .idMovimiento(movimiento.getIdMovimiento())
            .fecha(movimiento.getFecha())
            .tipoMovimiento(movimiento.getTipoMovimiento())
            .saldo(movimiento.getSaldo())
            .valor(movimiento.getValor())
            .idCuenta(movimiento.getIdCuenta().getIdCuenta())
            .build();
    }

    public static Movimiento mapperMovimiento(MovimientoDTO movimientoDTO){
        return Movimiento.builder()
            .idMovimiento(movimientoDTO.getIdMovimiento())
            .fecha(movimientoDTO.getFecha())
            .tipoMovimiento(movimientoDTO.getTipoMovimiento())
            .saldo(movimientoDTO.getSaldo())
            .valor(movimientoDTO.getValor())
            .idCuenta(cuentaRepositoryStatic.findById(movimientoDTO.getIdCuenta()).get())
            .build();
    }
}
