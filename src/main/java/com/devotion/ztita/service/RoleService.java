package com.devotion.ztita.service;

import com.devotion.ztita.dtos.role.RoleRequestDTO;
import com.devotion.ztita.dtos.role.RoleResponseDTO;
import com.devotion.ztita.mappers.roleMapper.RoleMapper;
import com.devotion.ztita.model.Role;
import com.devotion.ztita.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listarRoles() {
        return roleRepository.findAll();
    }

    public Role obtenerPorId(int id) {
        return roleRepository.findById(id).orElseThrow();
    }

    public Role guardar(Role role){
        return roleRepository.save(role);
    }

    public Role actualizar(int id, RoleRequestDTO dto){
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role no existente con el id" + id));
        RoleMapper.updateEntity(role,dto);
        return roleRepository.save(role);
    }

    public void eliminar(int id){
        if(!roleRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El Role con id %d no existe", id));
        }
        roleRepository.deleteById(id);
    }






}
