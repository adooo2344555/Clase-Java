package com.devsoft.orders_api.interfaces;

import com.devsoft.orders_api.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> findAll();
    ClienteDTO findById(Long id);
    ClienteDTO save(ClienteDTO clienteDTO);
    ClienteDTO findByNombre(String nombre);
    void delete(Long id);

}
