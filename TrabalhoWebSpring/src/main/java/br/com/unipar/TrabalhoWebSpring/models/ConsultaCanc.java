package br.com.unipar.TrabalhoWebSpring.models;

import br.com.unipar.TrabalhoWebSpring.enums.MotivoCancEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ConsultaCanc")
@ApiModel(description = "Representação de uma Consulta Cancelada")
public class ConsultaCanc {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado da ConsultaCanc")
    private Long id;
    @NotEmpty
    @NotBlank
    @NotNull
    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @NotEmpty
    @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    private MotivoCancEnum mtv_CancelamentoEnum;
}
