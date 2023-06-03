package br.com.unipar.TrabalhoWebSpring.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
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

    @NotEmpty
    @NotBlank
    @NotNull
    @OneToOne
    private Endereco endereco;


    private Boolean stAtivo;

}
