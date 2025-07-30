package com.devotion.ztita.controller;

import com.devotion.ztita.dtos.role.RoleRequestDTO;
import com.devotion.ztita.dtos.role.RoleResponseDTO;
import com.devotion.ztita.mappers.roleMapper.RoleMapper;
import com.devotion.ztita.model.Role;
import com.devotion.ztita.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*
    @GetMapping
    public List<Role> listar(){
        return roleService.listarRoles();
    }*/

    @GetMapping
    public List<RoleResponseDTO> listar() {
        return roleService.listarRoles().stream()
                .map(RoleMapper::toDTO)
                .toList();
    }


    /*
    @GetMapping("/{id}")
    public Role buscar(@PathVariable int id){
        return roleService.obtenerPorId(id);
    }*/

    @GetMapping("/{id}")
    public RoleResponseDTO buscar(@PathVariable int id){
        Role role = roleService.obtenerPorId(id);
        return RoleMapper.toDTO(role);
    }

    /*
    @PostMapping
    public Role guardar(@RequestBody Role role){
        return roleService.guardar(role);
    }*/

    @PostMapping
    public RoleResponseDTO guardar(@RequestBody RoleRequestDTO dto) {
        Role nuevo = RoleMapper.toEntity(dto);
        Role guardado = roleService.guardar(nuevo);
        return RoleMapper.toDTO(guardado);
    }

    /*
    @PutMapping("/{id}")
    public Role actualizar(@PathVariable int id, @RequestBody Role role){
        return roleService.actualizar(role);
    }*/

    @PutMapping("/{id}")
    public RoleResponseDTO actualizar(@PathVariable int id, @RequestBody RoleRequestDTO dto){
        Role actualizado = roleService.actualizar(id,dto);
        return RoleMapper.toDTO(actualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id){
        roleService.eliminar(id);
    }

}
