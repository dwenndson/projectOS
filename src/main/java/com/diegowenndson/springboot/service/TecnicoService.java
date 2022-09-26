package com.diegowenndson.springboot.service;

import java.util.List;
import java.util.Optional;

import com.diegowenndson.springboot.DTO.TecnicoDTO;
import com.diegowenndson.springboot.service.Exceptions.DataIntegratyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegowenndson.springboot.service.Exceptions.ObjectNotFoundsExceptions;
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
        return obj.orElseThrow(() -> new ObjectNotFoundsExceptions("Objeto não encontrado! id: " + id + ", Tipo: " +
                Tecnico.class.getName()));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        if(findByCPF(objDTO) != null){
            throw  new DataIntegratyViolationException("CPF ja cadastrado na base de dados");
        }
        return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(),
                objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico findByCPF(TecnicoDTO objDTO){
        Optional<Tecnico> obj = Optional.ofNullable(tecnicoRepository.findByCPF(objDTO.getCpf()));
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }
    
}
