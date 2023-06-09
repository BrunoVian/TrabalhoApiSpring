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
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Consulta")
@ApiModel(description = "Representação de uma Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado da Consulta")
    private Long id;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @NotNull
    @Column(name = "DataHoraConsulta")
    private LocalDateTime dtHr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDtHr() {
        return dtHr;
    }

    public void setDtHr(LocalDateTime dtHr) {
        this.dtHr = dtHr;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", medico=" + medico +
                ", dtHr=" + dtHr +
                '}';
    }
}
