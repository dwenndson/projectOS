package com.diegowenndson.springboot.service;

import java.util.List;
import java.util.Optional;

import com.diegowenndson.springboot.DTO.TecnicoDTO;
import com.diegowenndson.springboot.domain.Pessoa;
import com.diegowenndson.springboot.repository.PessoaRepository;
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

    @Autowired
    private PessoaRepository pessoaRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository){
        this.tecnicoRepository = tecnicoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundsExceptions(
                "Objeto não encontrado! id: " + id + ", Tipo: " +
                Tecnico.class.getName()));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        if(findByCPF(objDTO) != null){
            throw  new DataIntegratyViolationException("CPF já cadastrado na base de dados");
        }
        return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(),
                objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
        }

        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return tecnicoRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getList().size() >0){
            throw new DataIntegratyViolationException("Técnico possui ordens de serviços!");
        }
        tecnicoRepository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO){
        Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.getCpf() != null){
            return obj;
        }
        return null;
    }

}
