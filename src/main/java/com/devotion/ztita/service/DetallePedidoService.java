package com.devotion.ztita.service;

import com.devotion.ztita.model.DetallePedido;
import com.devotion.ztita.repository.DetallePedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido buscarPorId(int idDetalle) {
        return detallePedidoRepository.findById(idDetalle)
                .orElseThrow(() -> new EntityNotFoundException("DetallePedido con id " + idDetalle + " no existe"));
    }

    public List<DetallePedido> buscarPorPedido(int idPedido) {
        return detallePedidoRepository.findAll().stream()
                .filter(d -> d.getPedido() != null && d.getPedido().getIdPedido() == idPedido)
                .toList();
    }

    public List<DetallePedido> buscarPorProducto(int idProducto) {
        return detallePedidoRepository.findAll().stream()
                .filter(d -> d.getProducto() != null && d.getProducto().getIdProducto() == idProducto)
                .toList();
    }

    public DetallePedido crear(DetallePedido detallePedido) {
        detallePedido.setSubtotal(
                detallePedido.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(detallePedido.getCantidad()))
        );
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarPorId(int idDetalle, DetallePedido detallePedido) {
        DetallePedido existente = buscarPorId(idDetalle);
        existente.setCantidad(detallePedido.getCantidad());
        existente.setPrecioUnitario(detallePedido.getPrecioUnitario());
        existente.setPedido(detallePedido.getPedido());
        existente.setProducto(detallePedido.getProducto());
        existente.setSubtotal(
                detallePedido.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(detallePedido.getCantidad()))
        );
        return detallePedidoRepository.save(existente);
    }

    public void eliminar(int idDetalle) {
        if (!detallePedidoRepository.existsById(idDetalle)) {
            throw new EntityNotFoundException("DetallePedido con id " + idDetalle + " no existe");
        }
        detallePedidoRepository.deleteById(idDetalle);
    }
}
