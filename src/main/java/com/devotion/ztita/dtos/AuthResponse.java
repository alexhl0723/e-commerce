package com.devotion.ztita.dtos;

import com.devotion.ztita.model.Usuario;

public class AuthResponse {
    private String token;
    private int roleId; // Este es el campo clave

    public AuthResponse(String token, int roleId) {
        this.token = token;
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
