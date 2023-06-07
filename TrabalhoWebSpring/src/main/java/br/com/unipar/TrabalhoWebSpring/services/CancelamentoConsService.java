package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.ConsultaCanc;
import br.com.unipar.TrabalhoWebSpring.repositories.CancelamentoConsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CancelamentoConsService {

    @Autowired
    private CancelamentoConsRepository consultaCancRepository;

    public ConsultaCanc insert(ConsultaCanc consulta) throws Exception{

        consultaCancRepository.saveAndFlush(consulta);

        return consulta;
    }

    public ConsultaCanc edit(ConsultaCanc consulta) throws Exception{

        consultaCancRepository.saveAndFlush(consulta);

        return consulta;
    }

    public List<ConsultaCanc> findAll(){
        return consultaCancRepository.findAll();
    }

    public ConsultaCanc findById(Long id) throws Exception{
        Optional<ConsultaCanc> retorno = consultaCancRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Consulta com ID " + id + " Não Identificado");
    }

    public void validaCancelamento(ConsultaCanc consultaCanc) throws Exception{
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        LocalDateTime dataHoraConsulta = consultaCanc.getConsulta().getDtHr().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Verificar se a consulta está dentro da antecedência mínima de 24 horas para cancelamento
        if (dataHoraAtual.plusHours(24).isAfter(dataHoraConsulta)) {
            throw new RuntimeException("A consulta só pode ser cancelada com antecedência mínima de 24 horas.");
        }

    }

}
