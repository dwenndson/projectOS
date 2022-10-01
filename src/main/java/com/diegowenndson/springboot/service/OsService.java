package com.diegowenndson.springboot.service;

import com.diegowenndson.springboot.DTO.OsDTO;
import com.diegowenndson.springboot.domain.Cliente;
import com.diegowenndson.springboot.domain.OS;
import com.diegowenndson.springboot.domain.Tecnico;
import com.diegowenndson.springboot.domain.enuns.Prioridade;
import com.diegowenndson.springboot.domain.enuns.Status;
import com.diegowenndson.springboot.repository.OSRepository;
import com.diegowenndson.springboot.service.Exceptions.ObjectNotFoundsExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private OSRepository osRepository;
    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id){
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundsExceptions("Os Não encotnrada id: " +id+ ", Tipo: "+OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }

    public OS save(@Valid OsDTO os) {
        return fromDTO(os);
    }

    private OS fromDTO(OsDTO os){
        OS newObj = new OS();
        newObj.setId(os.getId());
        newObj.setObservacoes(os.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(os.getPrioridade()));
        newObj.setStatus(Status.toEnum(os.getStatus()));
        Tecnico tecnico = tecnicoService.findById(os.getTecnico());
        Cliente cliente = clienteService.findById(os.getCliente());
        newObj.setTecnico(tecnico);
        newObj.setCliente(cliente);
        return osRepository.save(newObj);
    }
}
