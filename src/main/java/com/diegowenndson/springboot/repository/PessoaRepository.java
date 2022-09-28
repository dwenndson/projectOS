package com.diegowenndson.springboot.repository;

import com.diegowenndson.springboot.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Pessoa findByCpf(@Param("cpf") String cpf);
}
