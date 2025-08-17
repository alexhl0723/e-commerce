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

    public List<RoleResponseDTO> listarRoles()
    {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }

    public RoleResponseDTO buscarPorId(int id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role no encontrado piipip" +
                + id));
        return RoleMapper.toDTO(role);
    }

    public RoleResponseDTO guardar(RoleRequestDTO dto){
        Role role = RoleMapper.toEntity(dto);
        Role guardado = roleRepository.save(role);
        return RoleMapper.toDTO(guardado);
    }

    public RoleResponseDTO actualizar(int id, RoleRequestDTO dto){
        Role role = buscarPorIdRealAqui(id);
        RoleMapper.updateEntity(role,dto);
        Role actualizado = roleRepository.save(role);
        return RoleMapper.toDTO(actualizado);
    }

    private Role buscarPorIdRealAqui(int id){
        return roleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("role no encontrado no lo digas"+ id) );
    }

    public void eliminar(int id){
        if(!roleRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El Role con id %d no existe", id));
        }
        roleRepository.deleteById(id);
    }


}
