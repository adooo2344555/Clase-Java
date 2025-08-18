package com.devsoft.orders_api.services;

import com.devsoft.orders_api.dto.UsuarioDTO;
import com.devsoft.orders_api.interfaces.IUsuarioService;

import java.util.List;

public class UsuarioService implements IUsuarioService {
    @Override
    public List<UsuarioDTO> findAll() {
        return List.of();
    }
}
