package com.diegowenndson.springboot.controller;

import com.diegowenndson.springboot.DTO.OsDTO;
import com.diegowenndson.springboot.domain.OS;
import com.diegowenndson.springboot.service.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/os")
public class OsController {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(new OsDTO(osService.findById(id)));

    }

    @GetMapping()
    public ResponseEntity<List<OsDTO>> findAll(){
        return ResponseEntity.ok().body(osService.findAll()
                .stream()
                .map(obj -> new OsDTO(obj))
                .collect(Collectors.toList()));
    }

    @PostMapping()
    public  ResponseEntity<OsDTO> save(@Valid @RequestBody OsDTO os){
        os = new OsDTO(osService.save(os));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(os.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
