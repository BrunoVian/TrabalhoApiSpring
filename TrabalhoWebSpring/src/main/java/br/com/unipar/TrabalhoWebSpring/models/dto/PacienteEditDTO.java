package br.com.unipar.TrabalhoWebSpring.models.dto;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;

public class PacienteEditDTO {

    private String nome;


    private String telefone;


    private Long enderecoId;

    public PacienteEditDTO() {
    }

    public PacienteEditDTO(String nome, String telefone, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.enderecoId = endereco.getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }
}