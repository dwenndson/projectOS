package com.diegowenndson.springboot.controller;

import com.diegowenndson.springboot.DTO.ClienteDTO;
import com.diegowenndson.springboot.domain.Cliente;
import com.diegowenndson.springboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        ClienteDTO obj = new ClienteDTO(clienteService.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> listobj =  clienteService.findAll()
                .stream()
                .map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listobj);
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente newObj = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        ClienteDTO newObj = new ClienteDTO(clienteService.update(id, clienteDTO));
        return ResponseEntity.ok().body(newObj);

    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
