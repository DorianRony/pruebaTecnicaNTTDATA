package com.ronyreyna.pruebatecnica.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.ronyreyna.pruebatecnica.Utils;
import com.ronyreyna.pruebatecnica.entity.Cliente;
import com.ronyreyna.pruebatecnica.entity.dto.ClienteDTO;
import com.ronyreyna.pruebatecnica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO guardarActualizarCliente(ClienteDTO clienteDTO){
        return Utils.mapperClienteDto(clienteRepository.save(Utils.mapperCliente(clienteDTO)));
    }

    public void eliminarCliente(Integer idCliente){
        clienteRepository.deleteById(idCliente);
    }

    public List<ClienteDTO> listarClientes(){
        return StreamSupport.stream(clienteRepository.findAll().spliterator(),false).map(Utils::mapperClienteDto).collect(
            Collectors.toList());
    }

    public ClienteDTO buscarCliente(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if(Objects.isNull(cliente)){
            return null;
        }else{
            return Utils.mapperClienteDto(cliente);
        }
    }
}
