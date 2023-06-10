package br.com.unipar.TrabalhoWebSpring.models.dto;

import br.com.unipar.TrabalhoWebSpring.enums.MotivoCancelamentoEnum;

public class ConsultaCancelamentoDTO {

    private Long consultaId;

    private MotivoCancelamentoEnum motivoCancelamentoEnum;

    public ConsultaCancelamentoDTO() {
    }

    public ConsultaCancelamentoDTO(Long consultaId, MotivoCancelamentoEnum motivoCancelamentoEnum) {
        this.consultaId = consultaId;
        this.motivoCancelamentoEnum = motivoCancelamentoEnum;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public MotivoCancelamentoEnum getMotivoCancelamentoEnum() {
        return motivoCancelamentoEnum;
    }

    public void setMotivoCancelamentoEnum(MotivoCancelamentoEnum motivoCancelamentoEnum) {
        this.motivoCancelamentoEnum = motivoCancelamentoEnum;
    }
}
