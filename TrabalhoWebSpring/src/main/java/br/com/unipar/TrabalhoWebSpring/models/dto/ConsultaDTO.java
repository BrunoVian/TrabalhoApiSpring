package br.com.unipar.TrabalhoWebSpring.models.dto;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultaDTO {

    private Long pacienteId;

    private Long medicoId;

    private LocalDateTime dtHr;

    public ConsultaDTO(Long pacienteId, Long medicoId, LocalDateTime dtHr) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dtHr = dtHr;
    }


    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public LocalDateTime getDtHr() {
        return dtHr;
    }

    public void setDtHr(LocalDateTime dtHr) {
        this.dtHr = dtHr;
    }
}
