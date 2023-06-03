package br.com.unipar.TrabalhoWebSpring.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Paciente")
@ApiModel(description = "Representação de um Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado do Paciente")
    private Long id;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String nome;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String email;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String telefone;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String CPF;

    @Valid
    @NotNull
    @OneToOne
    private Endereco endereco;

    private Boolean stAtivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(Boolean stAtivo) {
        this.stAtivo = stAtivo;
    }
}
