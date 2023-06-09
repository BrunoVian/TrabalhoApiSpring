package br.com.unipar.TrabalhoWebSpring.models.dto;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;

import java.sql.Date;

public class ConsultaDTO {

    private Long pacienteId;

    private Long medicoId;

    private Date dtHr;

    public ConsultaDTO(Long pacienteId, Long medicoId, Date dtHr) {
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

    public Date getDtHr() {
        return dtHr;
    }

    public void setDtHr(Date dtHr) {
        this.dtHr = dtHr;
    }
}
