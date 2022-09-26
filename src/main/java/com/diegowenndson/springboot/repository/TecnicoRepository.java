package com.diegowenndson.springboot.repository;

import com.diegowenndson.springboot.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    Tecnico findByCPF(@Param("cpf") String cpf);
}
