package com.devotion.ztita.controller;

import com.devotion.ztita.dtos.ProductoEstadoDTO;
import com.devotion.ztita.model.ImagenesProducto;
import com.devotion.ztita.model.Producto;
import com.devotion.ztita.model.Role;
import com.devotion.ztita.service.ProductoService;
import com.devotion.ztita.service.RoleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producto")
public class ProductosController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable int id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        productoService.prepararImagenes(producto);
        Producto guardado = productoService.crear(producto);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @RequestBody Producto producto) {
        productoService.prepararImagenes(producto);
        Producto actualizado = productoService.actualizarPorId(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        productoService.eliminar(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(@RequestParam String nombre) {
        try {
            List<Producto> productos = productoService.buscarPorNombre(nombre);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/estado")
    public ResponseEntity<Producto> actualizarEstado(@RequestBody ProductoEstadoDTO dto) {
        Producto actualizado = productoService.actualizarEstado(dto);
        return ResponseEntity.ok(actualizado);
    }


}
