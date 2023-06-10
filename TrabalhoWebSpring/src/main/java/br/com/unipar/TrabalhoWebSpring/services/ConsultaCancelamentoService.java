package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.ConsultaCancelamento;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaCancelamentoDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaCancelamentoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaCancelamentoService {

    @Autowired
    private ConsultaCancelamentoRepository consultaCancelamentoRespository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaCancelamento insert(ConsultaCancelamentoDTO request) throws Exception {

        ConsultaCancelamento consultaCancelamento = new ConsultaCancelamento();

        Consulta consulta = consultaRepository.findById(request.getConsultaId()).orElseThrow(()
                -> new EntityNotFoundException("Consulta com id: " + request.getConsultaId() + " não encontrada"));

        consultaCancelamento.setConsulta(consulta);
        consultaCancelamento.setMotivoCancelamentoEnum(request.getMotivoCancelamentoEnum());

        consultaCancelamentoRespository.saveAndFlush(consultaCancelamento);

        return consultaCancelamento;
    }

    public ConsultaCancelamento edit(ConsultaCancelamento consulta) throws Exception {

        consultaCancelamentoRespository.saveAndFlush(consulta);

        return consulta;
    }

    public List<ConsultaCancelamento> findAll() {
        return consultaCancelamentoRespository.findAll();
    }

    public ConsultaCancelamento findById(Long id) throws Exception {
        Optional<ConsultaCancelamento> retorno = consultaCancelamentoRespository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Consulta com ID " + id + " Não Identificado");
    }

    public void validaCancelamento(ConsultaCancelamento consultaCancelamento) throws Exception {
//        LocalDateTime dataHoraAtual = LocalDateTime.now();
//        LocalDateTime dataHoraConsulta = consultaCanc.getConsulta().getDtHr().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//
//        // Verificar se a consulta está dentro da antecedência mínima de 24 horas para cancelamento
//        if (dataHoraAtual.plusHours(24).isAfter(dataHoraConsulta)) {
//            throw new RuntimeException("A consulta só pode ser cancelada com antecedência mínima de 24 horas.");
//        }

    }

}
