package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.ConsultaCancelamento;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaCancelamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaCancelamentoService {

    @Autowired
    private ConsultaCancelamentoRepository consultaCancRepository;

    public ConsultaCancelamento insert(ConsultaCancelamento consulta) throws Exception{

        consultaCancRepository.saveAndFlush(consulta);

        return consulta;
    }

    public ConsultaCancelamento edit(ConsultaCancelamento consulta) throws Exception{

        consultaCancRepository.saveAndFlush(consulta);

        return consulta;
    }

    public List<ConsultaCancelamento> findAll(){
        return consultaCancRepository.findAll();
    }

    public ConsultaCancelamento findById(Long id) throws Exception{
        Optional<ConsultaCancelamento> retorno = consultaCancRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Consulta com ID " + id + " Não Identificado");
    }

    public void validaCancelamento(ConsultaCancelamento consultaCancelamento) throws Exception{
//        LocalDateTime dataHoraAtual = LocalDateTime.now();
//        LocalDateTime dataHoraConsulta = consultaCanc.getConsulta().getDtHr().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//
//        // Verificar se a consulta está dentro da antecedência mínima de 24 horas para cancelamento
//        if (dataHoraAtual.plusHours(24).isAfter(dataHoraConsulta)) {
//            throw new RuntimeException("A consulta só pode ser cancelada com antecedência mínima de 24 horas.");
//        }

    }

}
