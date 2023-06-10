package br.com.unipar.TrabalhoWebSpring.models;

import br.com.unipar.TrabalhoWebSpring.enums.MotivoCancelamentoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ConsultaCancelamento")
@ApiModel(description = "Representação de uma Consulta Cancelada")
public class ConsultaCancelamento {

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
    private MotivoCancelamentoEnum motivoCancelamentoEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public MotivoCancelamentoEnum getMotivoCancelamentoEnum() {
        return motivoCancelamentoEnum;
    }

    public void setMotivoCancelamentoEnum(MotivoCancelamentoEnum motivoCancelamentoEnum) {
        this.motivoCancelamentoEnum = motivoCancelamentoEnum;
    }
}
