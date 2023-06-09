package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.ConsultaCancelamento;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaCancelamentoDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaCancelamentoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        validaCancelamento(consultaCancelamento);

        consultaCancelamentoRespository.saveAndFlush(consultaCancelamento);

        return consultaCancelamento;
    }

    public ConsultaCancelamento edit(Long id, ConsultaCancelamentoDTO request) throws Exception {

        ConsultaCancelamento consultaCancelamento = consultaCancelamentoRespository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Cancelamento de Consulta com id: " + id + " não encontrado"));

        Consulta consulta = consultaRepository.findById(request.getConsultaId()).orElseThrow(()
                -> new EntityNotFoundException("Consulta com id: " + request.getConsultaId() + " não encontrada"));

        consultaCancelamento.setConsulta(consulta);
        consultaCancelamento.setMotivoCancelamentoEnum(request.getMotivoCancelamentoEnum());

        return consultaCancelamentoRespository.saveAndFlush(consultaCancelamento);

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
        Consulta consulta = consultaRepository.findById(consultaCancelamento.getConsulta().getId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta com id: " +
                        consultaCancelamento.getConsulta().getId() + " não encontrada"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limiteAntecedencia = now.plusHours(24);

        if (consulta.getDtHr().isBefore(limiteAntecedencia)) {
            throw new Exception("A consulta não pode ser cancelada com menos de 24 horas de antecedência.");
        }

    }

}
