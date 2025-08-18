package com.devsoft.orders_api.services;

import com.devsoft.orders_api.dto.CategoriaDTO;
import com.devsoft.orders_api.entities.Categoria;
import com.devsoft.orders_api.interfaces.ICategoriaService;
import com.devsoft.orders_api.repository.CategoriaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired // es una inyeccion de dependencia por campo tambien se puede por contructor

    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //metodo que devuelve una lista de entidsdes de categoria este es ejemplo
    public  List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if(categoria == null) return  null;
        return convertToDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaDTO save(CategoriaDTO dto) {
        Categoria catNueva = new Categoria();
        if(dto.getId() == null){
            // nuevo registro
            catNueva.setNombre(dto.getNombre());
        } else {
            // actualización
            catNueva.setId(dto.getId());
            catNueva.setNombre(dto.getNombre());
        }
        return convertToDTO(categoriaRepository.save(catNueva));
    }

    @Override
    public CategoriaDTO findByNombre(String nombre) {
        Categoria categoria = categoriaRepository.findByNombre(nombre);
        if(categoria == null) return null;
        return convertToDTO(categoria);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    private  CategoriaDTO convertToDTO(Categoria categoria){
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }
}
