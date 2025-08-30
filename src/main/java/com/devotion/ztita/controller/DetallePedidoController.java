package com.devotion.ztita.controller;

import com.devotion.ztita.model.DetallePedido;
import com.devotion.ztita.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedido> listar() {
        return detallePedidoService.listar();
    }

    @GetMapping("/{id}")
    public DetallePedido buscarPorId(@PathVariable int id) {
        return detallePedidoService.buscarPorId(id);
    }

    @GetMapping("/pedido/{idPedido}")
    public List<DetallePedido> buscarPorPedido(@PathVariable int idPedido) {
        return detallePedidoService.buscarPorPedido(idPedido);
    }

    @GetMapping("/producto/{idProducto}")
    public List<DetallePedido> buscarPorProducto(@PathVariable int idProducto) {
        return detallePedidoService.buscarPorProducto(idProducto);
    }

    @PostMapping
    public DetallePedido crear(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.crear(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizar(@PathVariable int id, @RequestBody DetallePedido detallePedido) {
        return detallePedidoService.actualizarPorId(id, detallePedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        detallePedidoService.eliminar(id);
    }
}
