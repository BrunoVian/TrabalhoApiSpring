package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query
    public List<Medico> findByNomeContainingAllIgnoringCase(String nome);

    @Query("SELECT m.nome AS nome, m.CRM AS CRM, m.email AS email, m.especialidade as especialidade FROM Medico m ORDER BY m.nome ASC")
    public List<MedicoRepository.MedicoProjection> findAllByOrderByNomeAsc();

    interface MedicoProjection {
        String getNome();
        String getCRM();
        String getEmail();
        String getEspecialidade();
    }


}
