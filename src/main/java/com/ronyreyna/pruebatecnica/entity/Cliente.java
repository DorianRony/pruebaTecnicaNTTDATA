package com.ronyreyna.pruebatecnica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity(name = "cliente")
public class Cliente extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid")
    private Integer idCliente;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private boolean estado;

    public Cliente(String nombre, String genero, Integer edad, String identificacion,
        String direccion,
        String telefono, Integer idCliente, String contrasena, Boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.idCliente = idCliente;
        this.contrasena = contrasena;
        this.estado = estado;
    }
}
