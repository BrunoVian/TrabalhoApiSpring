package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query
    public List<Consulta> findByPacienteAndDtHr(Paciente consultaPaciente, LocalDateTime consultaHr);
    @Query
    public List<Consulta> findByMedicoAndDtHr(Medico consultaMedico, LocalDateTime consultaHr);

    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND c.dtHr > :horaLimiteAnterior AND c.dtHr <= :horaLimite")
    List<Consulta> findConsultasProximas(Medico medico, LocalDateTime horaLimiteAnterior, LocalDateTime horaLimite);




}
