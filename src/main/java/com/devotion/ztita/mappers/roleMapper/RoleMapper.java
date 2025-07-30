package com.devotion.ztita.mappers.roleMapper;

import com.devotion.ztita.dtos.role.RoleRequestDTO;
import com.devotion.ztita.dtos.role.RoleResponseDTO;
import com.devotion.ztita.model.Role;

public class RoleMapper {



    public static RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setIdRol(role.getIdRol());
        dto.setNombreRol(role.getNombreRol());
        dto.setDescripcion(role.getDescripcion());
        return dto;
    }

    public static Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setIdRol(dto.getRoleId());
        role.setNombreRol(dto.getNombreRol());
        role.setDescripcion(dto.getDescripcion());
        return role;
    }

    public static void updateEntity(Role role, RoleRequestDTO dto) {
        role.setNombreRol(dto.getNombreRol());
        role.setDescripcion(dto.getDescripcion());
    }
}
