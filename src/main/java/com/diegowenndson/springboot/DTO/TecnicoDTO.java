package com.diegowenndson.springboot.DTO;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.diegowenndson.springboot.domain.Tecnico;

import javax.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo Nome é obrigatório")
    private String nome;
    @NotEmpty(message = "O campo cpf é obrigatório")
    @CPF
    private String cpf;
    @NotEmpty(message = "O campo telefone é obrigatório")
    private String telefone;

    public TecnicoDTO(Tecnico tecnico) {
        super();
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.telefone = tecnico.getTelefone();
    }

    public TecnicoDTO(){
        super();
    }
    
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
