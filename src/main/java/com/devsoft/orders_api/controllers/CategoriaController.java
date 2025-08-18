package com.devsoft.orders_api.controllers;


import com.devsoft.orders_api.dto.CategoriaDTO;
import com.devsoft.orders_api.entities.Categoria;
import com.devsoft.orders_api.interfaces.ICategoriaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<?> getAll(){
        List<CategoriaDTO> categoriaDTOList = categoriaService.findAll();
        return ResponseEntity.ok(categoriaDTOList);
    }

    //enpoint para opbtener una CategoriaDTO por el id
    @GetMapping("/categorias/{id}")  // no usages
    public ResponseEntity<?> getById(@PathVariable Long id){
        CategoriaDTO categoriaDTO = null;
        Map<String, Object> response = new HashMap<>();
        try{
            categoriaDTO = categoriaService.findById(id);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(categoriaDTO == null){
            response.put("message", "La categoria con ID: ".
                    concat(id.toString()).concat(" no existe en la base datos"));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoriaDTO>(categoriaDTO, HttpStatus.OK);
    }


    // si trae una imagen no fuera asi o si trae parametro fuera disntinto
    @PostMapping("/categorias")
    public ResponseEntity<?> save(@RequestBody CategoriaDTO dto){
        CategoriaDTO catPersisted = new CategoriaDTO();
        Map<String, Object> response = new HashMap<>(); //es lo qeu va a devolver hashMap

        try{
            CategoriaDTO catExiste =  categoriaService.findByNombre(dto.getNombre());
            if(catExiste != null && dto.getId() == null){
                response.put("message", "Ya existe una categoria con este nombre, digite otro");
                return new  ResponseEntity<Map<String,Object>>(response, HttpStatus.CONFLICT);
            }
            catPersisted = categoriaService.save(dto);
            response.put("message" , "Categoria registrada correctamente");
            response.put("categoria", catPersisted);
            return new  ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error al insertar el registro, intente de nuevo");
            response.put("error", e.getMessage());
            return new  ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //enpoint para actualizar una Categoria
    @PutMapping("/categorias/{id}")
    public ResponseEntity<?> update(@RequestBody CategoriaDTO dto, @PathVariable Long id){

        CategoriaDTO catActual = categoriaService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if(catActual == null){
            response.put("message", "La categoria con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CONFLICT);
        }

        CategoriaDTO catUpdated = null;
        try {
            CategoriaDTO catExiste = categoriaService.findByNombre(dto.getNombre());
            if(catExiste != null && !Objects.requireNonNull(catExiste).getId().equals(id)){
                response.put("message", "Ya existe una categoria con ese nombre en la base de datos, digite otro");
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CONFLICT);
            }

            catActual.setNombre(dto.getNombre());
            catUpdated = categoriaService.save(catActual);

        } catch (DataAccessException e) {
            response.put("error", "Error al actualizar un registro, intente de nuevo");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Categoria actualizada correctamente...!");
        response.put("categoria", catUpdated);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // endpoint para eliminar una Categoria
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        CategoriaDTO catActual = categoriaService.findById(id);
        if(catActual == null){
            response.put("message", "No se puede eliminar la categoria con ID: "
                    .concat(id.toString()).concat(" no existe en la base datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            categoriaService.delete(id);
        }catch (DataAccessException e){
            response.put("message", "No se puede eliminar la categoria, ya tiene menus asociados");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Categoria eliminada...!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
