package com.diegowenndson.springboot.controller;

import com.diegowenndson.springboot.domain.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diegowenndson.springboot.DTO.TecnicoDTO;
import com.diegowenndson.springboot.service.TecnicoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
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
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO objDTO){
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
