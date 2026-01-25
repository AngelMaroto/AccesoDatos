package com.angel.apivuelos.controller;

import com.angel.apivuelos.dto.CompaniaDTO;
import com.angel.apivuelos.service.CompaniaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companias")
@SecurityRequirement(name = "bearerAuth")
public class CompaniaController {

    @Autowired
    private CompaniaService companiaService;

    @PostMapping
    public void registrar(@RequestBody CompaniaDTO dto) {
        companiaService.registrar(dto);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id) {
        companiaService.borrar(id);
    }

    @PutMapping("/{id}")
    public void modificar(@PathVariable Integer id, @RequestBody CompaniaDTO dto) {
        companiaService.modificar(id, dto);
    }
}
