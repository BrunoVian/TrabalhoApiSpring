package br.com.unipar.TrabalhoWebSpring.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
@Entity
@Table(name = "Consulta")
@ApiModel(description = "Representação de uma Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado da Consulta")
    private Long id;

    @NotEmpty
    @NotBlank
    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @NotEmpty
    @NotBlank
    @NotNull
    private Date dtHr;
}
