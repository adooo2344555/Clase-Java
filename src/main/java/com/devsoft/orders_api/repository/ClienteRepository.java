package com.devsoft.orders_api.repository;

import com.devsoft.orders_api.entities.Categoria;
import com.devsoft.orders_api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNombre(String nombre);
}
