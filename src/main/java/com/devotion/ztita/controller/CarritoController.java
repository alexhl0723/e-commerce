package com.devotion.ztita.controller;

import com.devotion.ztita.model.Carrito;
import com.devotion.ztita.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // ✅ Obtener el carrito por idCliente
    @GetMapping
    public List<Carrito> getCarritos(@RequestParam int idCliente) {
        return carritoService.getCarritoPorIdCliente(idCliente);
    }

    // ✅ Eliminar un ítem del carrito por su ID
    @DeleteMapping("/{id}")
    public void eliminarCarrito(@PathVariable int id) {
        carritoService.eliminarCarrito(id);
    }

    // ✅ Agregar un nuevo producto al carrito
    @PostMapping("/agregar")
    public Carrito crearCarrito(@RequestBody Carrito carrito){
        return carritoService.agregarCarrito(carrito);
    }

    // ✅ Agregar o actualizar un producto en el carrito (evita duplicados)
    @PostMapping("/agregar-o-actualizar")
    public Carrito agregarOActualizar(@RequestBody Carrito carrito){
        return carritoService.agregarOActualizarCarrito(carrito);
    }

    // ✅ Actualizar cantidad de un producto existente en el carrito
    @PutMapping("/actualizar-cantidad")
    public Carrito actualizarCantidadCarrito(
            @RequestParam int idCliente,
            @RequestParam int cantidad) {
        return carritoService.actualizarCantidadProducto(idCliente, cantidad);
    }
}

