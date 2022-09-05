package com.diegowenndson.springboot.repository;

import com.diegowenndson.springboot.domain.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {
}
