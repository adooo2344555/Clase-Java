package com.devsoft.orders_api.services;

import com.devsoft.orders_api.dto.MesaDTO;
import com.devsoft.orders_api.interfaces.IMesaService;

import java.util.List;

public class MesaService implements IMesaService {
    @Override
    public List<MesaDTO> findAll() {
        return List.of();
    }
}
