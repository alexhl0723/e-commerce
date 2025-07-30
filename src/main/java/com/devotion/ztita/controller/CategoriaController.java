package com.devotion.ztita.controller;

import com.devotion.ztita.model.Categoria;
import com.devotion.ztita.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<Categoria> listar(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable int id){
        return categoriaService.obtenerPorId(id);
    }

    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria){
        return categoriaService.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable int id,@RequestBody Categoria categoria){
        return categoriaService.actualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id){
        categoriaService.eliminar(id);
    }

}
