package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.ConsultaCanc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelamentoConsRepository extends JpaRepository<ConsultaCanc, Long> {

}
