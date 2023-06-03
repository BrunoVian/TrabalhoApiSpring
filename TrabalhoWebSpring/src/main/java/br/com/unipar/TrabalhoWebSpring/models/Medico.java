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

}
