package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query
    public List<Paciente> findByNomeContainingAllIgnoringCase(String nome);

    @Query("SELECT p.nome AS nome, p.CPF AS cpf, p.email AS email FROM Paciente p ORDER BY p.nome ASC")
    List<PacienteProjection> findAllByOrderByNomeAsc();

        interface PacienteProjection {
            String getNome();
            String getCpf();
            String getEmail();
    }

}
