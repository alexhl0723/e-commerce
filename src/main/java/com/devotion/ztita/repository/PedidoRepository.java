package com.devotion.ztita.repository;

import com.devotion.ztita.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByIdUsuario(int idUsuario);
    List<Pedido> findByEstado(String estado);

}
