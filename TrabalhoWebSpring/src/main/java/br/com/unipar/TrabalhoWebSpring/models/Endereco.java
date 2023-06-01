package br.com.unipar.TrabalhoWebSpring.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Endereco")
@ApiModel(description = "Representação de um Endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado do Médico")
    private Long id;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String logradouro;

    @Size(min = 0, max = 255)
    private String complemento;

    @Size(min = 3, max = 255)
    @NotEmpty
    @NotBlank
    @NotNull
    private String bairro;

    @Size(min = 0, max = 255)
    private int numero;
}
