package com.devotion.ztita.repository;

import com.devotion.ztita.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.estado = :estado WHERE c.idCategoria = :id")
    void actualizarEstado(int id, byte estado);
}
