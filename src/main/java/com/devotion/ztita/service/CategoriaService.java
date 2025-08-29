package com.devotion.ztita.service;

import com.devotion.ztita.model.Categoria;
import com.devotion.ztita.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(int id) {
        return categoriaRepository.findById(id).orElseThrow();
    }

    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminar(int id) {
        if(!categoriaRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El categoria con id %d no existe", id));
        }
        categoriaRepository.deleteById(id);
    }

    public Categoria actualizar(int id, Categoria categoria) {
        if(!categoriaRepository.existsById(id)){
            throw new EntityNotFoundException("La categoria no existe" + id);
        }
        categoria.setIdCategoria(id); // muy importante
        return categoriaRepository.save(categoria);
    }
    public Categoria cambiarEstado(int id, boolean estado) {
        byte estadoByte = (byte) (estado ? 1 : 0);
        categoriaRepository.actualizarEstado(id, estadoByte);
        return categoriaRepository.findById(id).orElse(null);
    }
}

