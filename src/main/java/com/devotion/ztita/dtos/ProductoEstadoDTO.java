package com.devotion.ztita.dtos;

public class ProductoEstadoDTO {
    private int idProducto;
    private byte estado;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
}
