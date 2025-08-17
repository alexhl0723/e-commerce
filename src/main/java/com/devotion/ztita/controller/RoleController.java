package com.devotion.ztita.controller;

import com.devotion.ztita.dtos.role.RoleRequestDTO;
import com.devotion.ztita.dtos.role.RoleResponseDTO;
import com.devotion.ztita.mappers.roleMapper.RoleMapper;
import com.devotion.ztita.model.Role;
import com.devotion.ztita.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleResponseDTO> listar() {
        return roleService.listarRoles();
    }

    @GetMapping("/{id}")
    public RoleResponseDTO buscar(@PathVariable int id){
        return roleService.buscarPorId(id);
    }

    @PostMapping
    public RoleResponseDTO guardar(@Valid @RequestBody RoleRequestDTO dto) {
        return roleService.guardar(dto);
    }

    @PutMapping("/{id}")
    public RoleResponseDTO actualizar(@PathVariable int id, @RequestBody RoleRequestDTO dto){
        return roleService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id){
        roleService.eliminar(id);
    }

}
