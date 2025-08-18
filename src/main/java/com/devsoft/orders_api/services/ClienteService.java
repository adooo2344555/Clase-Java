package com.devsoft.orders_api.services;

import com.devsoft.orders_api.dto.CategoriaDTO;
import com.devsoft.orders_api.dto.ClienteDTO;
import com.devsoft.orders_api.entities.Categoria;
import com.devsoft.orders_api.entities.Cliente;
import com.devsoft.orders_api.interfaces.IClienteService;
import com.devsoft.orders_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());    }

    @Override
    public ClienteDTO findById(Long id) {
        return null;
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return null;
    }

    @Override
    public ClienteDTO findByNombre(String nombre) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getTipoCliente()
        );
    }
}
