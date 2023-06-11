package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query
    public List<Medico> findByNomeContainingAllIgnoringCase(String nome);

    @Query("SELECT m.nome AS nome, m.CRM AS CRM, m.email AS email, m.especialidade as especialidade FROM Medico m ORDER BY m.nome ASC")
    public List<MedicoRepository.MedicoProjection> findAllByOrderByNomeAsc();

    @Query("SELECT m FROM Medico m WHERE m.stAtivo = true AND NOT EXISTS (SELECT c FROM Consulta c WHERE c.medico = m AND c.dtHr = :dateTime)")
    List<Medico> findByDisponivelEm(LocalDateTime dateTime);

    interface MedicoProjection {
        String getNome();
        String getCRM();
        String getEmail();
        String getEspecialidade();
    }


}
