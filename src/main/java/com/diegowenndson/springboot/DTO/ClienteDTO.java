package com.diegowenndson.springboot.DTO;

import com.diegowenndson.springboot.domain.Cliente;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ConstructorBinding
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo Nome é obrigatório")
    private String nome;
    @NotEmpty(message = "O campo cpf é obrigatório")
    @CPF
    private String cpf;
    @NotEmpty(message = "O campo telefone é obrigatório")
    private String telefone;

    public ClienteDTO(Cliente cliente) {
        super();
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
    }

    public ClienteDTO() {super();}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
