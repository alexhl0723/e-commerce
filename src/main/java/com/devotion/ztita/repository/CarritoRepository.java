package com.devotion.ztita.repository;

import com.devotion.ztita.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    List<Carrito> findByIdUsuario(Integer idUsuario);
    Optional<Carrito> findByIdUsuarioAndIdProducto(Integer idUsuario, Integer idProducto);


}
