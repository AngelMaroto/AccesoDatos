package com.angel.apivuelos.service;

import com.angel.apivuelos.dto.CompaniaDTO;
import com.angel.apivuelos.model.Compania;
import com.angel.apivuelos.repository.CompaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompaniaService {

    @Autowired
    private CompaniaRepository companiaRepository;

    public void registrar(CompaniaDTO dto) {
        Compania c = new Compania();
        c.setIdCompania(dto.getIdCompania());
        c.setNombreCompania(dto.getNombreCompania());
        companiaRepository.save(c);
    }

    public void borrar(Integer id) {
        companiaRepository.deleteById(id);
    }

    public void modificar(Integer id, CompaniaDTO dto) {
        companiaRepository.findById(id).ifPresent(c -> {
            c.setNombreCompania(dto.getNombreCompania());
            companiaRepository.save(c);
        });
    }
}
