package br.com.unipar.TrabalhoWebSpring.repositories;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query
    public List<Endereco> findByLogradouroContainingAllIgnoringCase(String logradouro);

}
