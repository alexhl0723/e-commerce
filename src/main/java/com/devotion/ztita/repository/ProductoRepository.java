package com.devotion.ztita.repository;

import com.devotion.ztita.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    Optional<Producto> findById(int id);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> buscarProductosPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.estado = :estado WHERE p.idProducto = :id")
    void actualizarEstado(@Param("id") int id, @Param("estado") byte estado);
}
