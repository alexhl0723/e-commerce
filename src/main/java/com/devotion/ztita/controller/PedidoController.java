package com.devotion.ztita.controller;

import com.devotion.ztita.model.Pedido;
import com.devotion.ztita.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8084"})
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable int id) {
        return pedidoService.buscarPorId(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Pedido> buscarPorUsuario(@PathVariable int idUsuario) {
        return pedidoService.buscarPorUsuario(idUsuario);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> buscarPorEstado(@PathVariable String estado) {
        return pedidoService.buscarPorEstado(estado);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.crear(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable int id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPorId(id, pedido);
    }

    @PatchMapping("/{id}/estado")
    public Pedido actualizarEstado(@PathVariable int id, @RequestParam String estado) {
        return pedidoService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoService.eliminar(id);
    }
}

