package com.devsoft.orders_api.interfaces;

import com.devsoft.orders_api.dto.CategoriaDTO;
import com.devsoft.orders_api.dto.MesaDTO;

import java.util.List;

public interface IMesaService {
    List<MesaDTO> findAll();

}
