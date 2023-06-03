package br.com.unipar.TrabalhoWebSpring.models;

import br.com.unipar.TrabalhoWebSpring.enums.EspecialidadeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Medico")
@ApiModel(description = "Representação de um Médico")
public class Medico {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado do Médico")
    private Long id;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String nome;

    @NotEmpty
    @NotBlank
    @NotNull
    private String email;

    @NotEmpty
    @NotBlank
    @NotNull
    @Size(min=11,max = 11)
    private String telefone;

    @NotEmpty
    @NotBlank
    @NotNull
    @Size(min=12,max = 12)
    private String CRM;

    @NotEmpty
    @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    private EspecialidadeEnum especialidade;

    @NotEmpty
    @NotBlank
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

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
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
