package com.diegowenndson.springboot.service;

import com.diegowenndson.springboot.DTO.ClienteDTO;
import com.diegowenndson.springboot.domain.Cliente;
import com.diegowenndson.springboot.domain.Pessoa;
import com.diegowenndson.springboot.repository.ClienteRepository;
import com.diegowenndson.springboot.repository.PessoaRepository;
import com.diegowenndson.springboot.service.Exceptions.DataIntegratyViolationException;
import com.diegowenndson.springboot.service.Exceptions.ObjectNotFoundsExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundsExceptions(
                "Cliente com o Id: " + id + "não encontrado, tipo: " + Cliente.class.getName()
        ));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO){
        if(findByCPF(clienteDTO) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
        }
        return clienteRepository.save(new Cliente(null, clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getTelefone()));
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO){
        Cliente oldObj = findById(id);
        if(findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
        }
        oldObj.setNome(clienteDTO.getNome());
        oldObj.setCpf(clienteDTO.getCpf());
        oldObj.setTelefone(clienteDTO.getTelefone());
        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Pessoa Possui Ordens de serviço cadastradas, não pode ser deletado");
        }
        clienteRepository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());

        if (obj != null) {
            return obj;
        }
        return null;
    }
}
