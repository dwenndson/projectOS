package com.diegowenndson.springboot.controller;

import com.diegowenndson.springboot.domain.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diegowenndson.springboot.DTO.TecnicoDTO;
import com.diegowenndson.springboot.service.TecnicoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
         TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
         return ResponseEntity.ok().body(tecnicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> listDTO = tecnicoService.findAll()
                .stream()
                .map(obj -> new TecnicoDTO(obj))
                .collect(Collectors.toList());
//        List<Tecnico> tecnicos = new tecnicoService.findAll();
//        List<TecnicoDTO> listDTO = new ArrayList<>();
//        Old method, for population new array
//        for(Tecnico obj: tecnicos){
//            listDTO.add(new TecnicoDTO(obj));
//        }
//        new Method for population array, the java 8+
//        tecnicos.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody TecnicoDTO objDTO){
        TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
