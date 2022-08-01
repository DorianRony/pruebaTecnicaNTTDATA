package com.ronyreyna.pruebatecnica.entity.dto;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.ronyreyna.pruebatecnica.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Integer idCuenta;
    private String numeroCuenta;
    private String tipoCuenta;
    private Boolean estado;
    private BigDecimal saldoIncial;
    private Integer idCliente;
}
