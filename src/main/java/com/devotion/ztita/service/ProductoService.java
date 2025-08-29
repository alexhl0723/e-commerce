package com.devotion.ztita.service;

import com.devotion.ztita.model.ImagenesProducto;
import com.devotion.ztita.model.Producto;
import com.devotion.ztita.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    public List<Producto> listar(){
        return productoRepository.findAll();
    }
    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    public Producto buscarPorId(int id){
        return productoRepository.findById(id).orElseThrow();
    }
    public Producto crear(Producto producto) { return productoRepository.save(producto);}

    public Producto actualizarPorId(int id,Producto producto){
        if(!productoRepository.existsById(id)){
            throw new EntityNotFoundException("El producto no existe" + id);
        }
        producto.setIdProducto(id);
        return productoRepository.save(producto);
    }
    public void eliminar(int id){
        if(!productoRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El producto con id %d no existe", id));
        }
        productoRepository.deleteById(id);
    }
    //este medoto es un refactor brutal
    public void prepararImagenes(Producto producto) {
        List<ImagenesProducto> imagenes = producto.getImagenesProductos();

        if (imagenes != null && !imagenes.isEmpty()) {
            for (int i = 0; i < imagenes.size(); i++) {
                ImagenesProducto img = imagenes.get(i);
                img.setProducto(producto);
                img.setOrden(i);
                img.setEsPrincipal((byte) (i == 0 ? 1 : 0));
            }
        }
    }

    public void cambiarEstado(int id, boolean estado) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("El producto con id " + id + " no existe");
        }
        byte estadoByte = (byte) (estado ? 1 : 0);
        productoRepository.actualizarEstado(id, estadoByte);
    }
}
