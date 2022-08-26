package com.diegowenndson.springboot.domain.enuns;

public enum Status {
    OPEN(0, "OPEN"),
    PROCESS(1, "PROCESS"),
    CLOSED(3, "CLOSED");

    private Integer cod;
    private String descricao;

    Status(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Status x: Status.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida" + cod);
    }
}