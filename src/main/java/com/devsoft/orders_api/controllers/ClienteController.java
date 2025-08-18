package com.devsoft.orders_api.controllers;


import com.devsoft.orders_api.dto.CategoriaDTO;
import com.devsoft.orders_api.dto.ClienteDTO;
import com.devsoft.orders_api.interfaces.ICategoriaService;
import com.devsoft.orders_api.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/categorias")
    public ResponseEntity<?> getAll(){
        List<ClienteDTO> clienteDTOList = clienteService.findAll();
        return ResponseEntity.ok(clienteDTOList);
    }
}
