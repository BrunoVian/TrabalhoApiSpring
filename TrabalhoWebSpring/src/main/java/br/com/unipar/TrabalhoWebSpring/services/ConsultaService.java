package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.ConsultaRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.MedicoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                .orElseThrow(() -> new EntityNotFoundException("Medico com id: " + request.getMedicoId() + " não encontrado"));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDtHr(request.getDtHr());

        validaConsulta(consulta);

        consultaRepository.saveAndFlush(consulta);

        return consulta;
    }

    public Consulta edit(Long id, ConsultaDTO request) throws Exception {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta com id: " + id + " não encontrada"));

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + request.getPacienteId() + " não encontrado"));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new EntityNotFoundException("Medico com id: " + request.getMedicoId() + " não encontrado"));

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDtHr(request.getDtHr());

        validaConsulta(consulta);

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

    public void validarAntecedencia(Consulta consulta) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if (now.plusMinutes(30).isAfter(consulta.getDtHr())) {
            throw new Exception("A consulta deve ser agendada com uma antecedência mínima de 30 minutos.");
        }
    }

    public void validaDisponibilidadeMedico(Consulta consulta) throws Exception {
        LocalDateTime dataHoraConsulta = consulta.getDtHr();
        Medico medico = consulta.getMedico();

        List<Consulta> consultasAgendadas = consultaRepository.findByMedicoAndDtHr(medico, dataHoraConsulta);
        if (!consultasAgendadas.isEmpty()) {
            throw new Exception("O médico já possui uma consulta agendada na mesma data e hora.");
        }

        LocalDateTime horaLimite = dataHoraConsulta.plusHours(1);
        LocalDateTime horaLimiteAnterior = dataHoraConsulta.minusHours(1);
        List<Consulta> consultasProximas = consultaRepository.findConsultasProximas(medico, horaLimiteAnterior, horaLimite);
        if (!consultasProximas.isEmpty()) {
            throw new Exception("O médico possui uma consulta agendada dentro de 1 hora após o horário de agendamento.");
        }
    }

    public void validaDisponibilidadePaciente(Consulta consulta) throws Exception{
        Paciente paciente = consulta.getPaciente();
        LocalDate dataConsulta = consulta.getDtHr().toLocalDate();

        List<Consulta> consultas = consultaRepository.findByPacienteAndData(paciente, dataConsulta);

        if (!consultas.isEmpty()) {
            throw new Exception("Já existe uma consulta agendada para o paciente no mesmo dia.");
        }
    }

//    public Medico escolherMedicoAleatoriamente(Consulta consulta) throws Exception {
//
//        List<Medico> medicosDisponiveis = medicoRepository.findByDisponivelEm(dtHr);
//
//        if (medicosDisponiveis.isEmpty()) {
//            throw new Exception("Não há médicos disponíveis na data/hora especificada.");
//        }
//
//        validaConsulta(consulta);
//
//        Random random = new Random();
//        int index = random.nextInt(medicosDisponiveis.size());
//
//        return medicosDisponiveis.get(index);
//
//    }

    public void validaConsulta(Consulta consulta) throws Exception{
        validaDisponibilidadePaciente(consulta);
        validaDisponibilidadeMedico(consulta);
        validaStatus(consulta);
        validarHorarioFuncionamento(consulta);
        validarAntecedencia(consulta);
    }


}