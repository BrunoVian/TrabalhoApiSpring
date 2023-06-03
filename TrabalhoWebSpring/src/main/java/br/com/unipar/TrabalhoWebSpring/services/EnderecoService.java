package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco insert(Endereco endereco) throws Exception{

        enderecoRepository.saveAndFlush(endereco);

        return endereco;
    }

    public Endereco edit(Endereco endereco) throws Exception{

        enderecoRepository.saveAndFlush(endereco);

        return endereco;
    }

    public List<Endereco> findAll(){
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) throws Exception{
        Optional<Endereco> retorno = enderecoRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Endereço com ID " + id + " Não Identificado");
    }

    public List<Endereco> findByFilters(String logradouro) {

        return enderecoRepository.findByLogradouroContainingAllIgnoringCase(logradouro);

    }


}
