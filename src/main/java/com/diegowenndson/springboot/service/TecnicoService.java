package com.diegowenndson.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegowenndson.springboot.domain.Tecnico;
import com.diegowenndson.springboot.repository.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository){
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null);
    }
    
}
