package com.devotion.ztita.dtos.role;

import com.devotion.ztita.model.Role;

public class RoleResponseDTO {
    private int idRol;
    private String nombreRol;
    private String descripcion;

    public RoleResponseDTO() {}

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
