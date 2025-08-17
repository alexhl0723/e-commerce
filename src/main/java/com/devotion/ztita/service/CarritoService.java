package com.devotion.ztita.service;

import com.devotion.ztita.model.Carrito;
import com.devotion.ztita.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> getCarritoPorIdCliente(Integer idCliente) {
        if (idCliente == null) {
            return carritoRepository.findAll();
        }
        return carritoRepository.findByIdUsuario(idCliente);
    }

    public void eliminarCarrito(Integer idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    public Carrito agregarCarrito(Carrito carrito){
        carrito.setFechaAgregado(new Date());
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCantidadProducto(Integer idCarrito, int nuevaCantidad){
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(()-> new RuntimeException("Carrito no encontrado"+idCarrito) );
        carrito.setCantidad(nuevaCantidad);
        return carritoRepository.save(carrito);
    }

    public Carrito agregarOActualizarCarrito(Carrito nuevo){
        Optional<Carrito> existe = carritoRepository.findByIdUsuarioAndIdProducto(nuevo.getIdUsuario()
        , nuevo.getIdProducto());
        if(existe.isPresent()){
            Carrito carrito = existe.get();
            carrito.setCantidad(nuevo.getCantidad());
            return carritoRepository.save(carrito);
        }
        nuevo.setFechaAgregado(new Date());
        return carritoRepository.save(nuevo);

    }

}
