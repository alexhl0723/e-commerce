package com.devotion.ztita.service;

import com.devotion.ztita.model.Pedido;
import com.devotion.ztita.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(int idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new EntityNotFoundException("El pedido con id " + idPedido + " no existe"));
    }

    public List<Pedido> buscarPorUsuario(int idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

    public List<Pedido> buscarPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido crear(Pedido pedido) {
        pedido.setFechaPedido(new Date()); // fecha actual al registrar
        pedido.setEstado("Pendiente"); // estado por defecto
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPorId(int id, Pedido pedido) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("El pedido con id " + id + " no existe");
        }
        pedido.setIdPedido(id);
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarEstado(int idPedido, String nuevoEstado) {
        Pedido pedido = buscarPorId(idPedido);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public void eliminar(int idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new EntityNotFoundException("El pedido con id " + idPedido + " no existe");
        }
        pedidoRepository.deleteById(idPedido);
    }
}
