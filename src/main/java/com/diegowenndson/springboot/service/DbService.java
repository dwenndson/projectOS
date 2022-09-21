package com.diegowenndson.springboot.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegowenndson.springboot.domain.Cliente;
import com.diegowenndson.springboot.domain.OS;
import com.diegowenndson.springboot.domain.Tecnico;
import com.diegowenndson.springboot.domain.enuns.Prioridade;
import com.diegowenndson.springboot.domain.enuns.Status;
import com.diegowenndson.springboot.repository.ClienteRepository;
import com.diegowenndson.springboot.repository.OSRepository;
import com.diegowenndson.springboot.repository.TecnicoRepository;

@Service
public class DbService {
    
    @Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
    
    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Diego W", "853.121.020-83", "(85) 99999-9999");
		Cliente c1 = new Cliente(null, "Betina Campos", "192.820.740-56", "(85) 98888-8888");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.PROCESS, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
    }
    
}
