package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaDTO;
import br.com.unipar.TrabalhoWebSpring.models.dto.PacienteEditDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.MedicoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public Consulta insert(ConsultaDTO request) throws Exception {
        Consulta consulta = new Consulta();

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + request.getPacienteId() + " não encontrado"));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + request.getMedicoId() + " não encontrado"));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDtHr(request.getDtHr());

        validaStatus(consulta);
        validarHorarioFuncionamento(consulta);
        agendarConsulta(consulta);

        consultaRepository.saveAndFlush(consulta);

        return consulta;
    }

    public Consulta edit(ConsultaDTO request) throws Exception {

        Consulta consulta = new Consulta();

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + request.getPacienteId() + " não encontrado"));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + request.getMedicoId() + " não encontrado"));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDtHr(request.getDtHr());

        consultaRepository.saveAndFlush(consulta);

        return consulta;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Consulta findById(Long id) throws Exception {
        Optional<Consulta> retorno = consultaRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Consulta com ID " + id + " Não Identificado");
    }

    public void validaStatus(Consulta consulta) throws Exception {
        if (consulta.getMedico().getStAtivo() == false) {
            throw new Exception("O médico está inativo, favor escolher outro!");
        }

        if (consulta.getPaciente().getStAtivo() == false) {
            throw new Exception("O paciente está inativo, favor escolher outro!");
        }
    }

//    public void validaHoraConsulta (Consulta consulta) throws Exception{
//        LocalDateTime localDateTime;
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(consulta.getDtHr());
//        int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
//        LocalDateTime horarioInicio = LocalDateTime.of(7, 0);
//        LocalDateTime horarioFim = LocalDateTime.of(19, 0);
//        LocalDateTime horarioConsulta = consulta.getDtHr().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
//        LocalDateTime dataHoraAtual = LocalDateTime.now();
//        LocalDateTime dataHoraConsulta = consulta.getDtHr().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//
//        // Verificar se a consulta foi agendada com antecedência mínima de 30 minutos
//        if (dataHoraAtual.plusMinutes(30).isAfter(dataHoraConsulta)) {
//            throw new Exception("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
//        }
//
//        // Verificar se a duração da consulta é de 1 hora
//        LocalDateTime dataHoraFimConsulta = dataHoraConsulta.plusHours(1);
//        if (dataHoraFimConsulta.isAfter(dataHoraConsulta)) {
//            throw new Exception("A consulta deve ter duração fixa de 1 hora.");
//        }
//    }
//
//    public void validaAgendamento(Consulta consulta)throws Exception{
//        List<Consulta> consultasDoPaciente = consultaRepository.findByPacienteAndDtHr(consulta.getPaciente(), consulta.getDtHr());
//        if (!consultasDoPaciente.isEmpty()) {
//            throw new Exception("Já existe uma consulta agendada para o paciente nessa data!");
//        }
//
//        // Não permitir o agendamento de uma consulta com um médico que já possui outra consulta agendada na mesma data/hora
//        List<Consulta> consultasDoMedico = consultaRepository.findByMedicoAndDtHr(consulta.getMedico(), consulta.getDtHr());
//        if (!consultasDoMedico.isEmpty()) {
//            throw new Exception("O médico já possui uma consulta agendada nessa data/hora!");
//        }
//    }

    public static void validarHorarioFuncionamento(Consulta consulta) throws Exception {
        DayOfWeek diaSemana = consulta.getDtHr().getDayOfWeek();

        LocalTime horaConsulta = consulta.getDtHr().toLocalTime();
        LocalTime horaInicioFuncionamento = LocalTime.of(7, 0);
        LocalTime horaFimFuncionamento = LocalTime.of(19, 0);

        if (diaSemana == DayOfWeek.SUNDAY) {
            throw new Exception("A consulta está fora do dia de funcionamento da clínica. (Seg à Sab)");
        }

        if (horaConsulta.isBefore(horaInicioFuncionamento) || horaConsulta.isAfter(horaFimFuncionamento)) {
            throw new Exception("A consulta está fora do horário de funcionamento da clínica. (07:00 às 19:00)");
        }
    }

    public void agendarConsulta(Consulta consulta) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if (now.plusMinutes(30).isAfter(consulta.getDtHr())) {
            throw new Exception("A consulta deve ser agendada com uma antecedência mínima de 30 minutos.");
        }
    }

    private Medico escolherMedicoDisponivel(Consulta consulta) {
        List<Consulta> consultasDoMedico = consultaRepository.findByMedicoAndDtHr(consulta.getMedico(), consulta.getDtHr());
        if (consultasDoMedico.isEmpty()) {
            // Escolha aleatoriamente um médico disponível
            Random random = new Random();
            int index = random.nextInt(consultasDoMedico.size());
            return consultasDoMedico.get(index).getMedico();
        }

        return null; // Retorne null se não houver médicos disponíveis
    }


}
