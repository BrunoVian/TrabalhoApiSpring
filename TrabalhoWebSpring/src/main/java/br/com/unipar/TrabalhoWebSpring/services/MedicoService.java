package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico insert(Medico medico) throws Exception{

        medicoRepository.saveAndFlush(medico);

        return medico;
    }

    public Medico edit(Medico medico) throws Exception{

        medicoRepository.saveAndFlush(medico);

        return medico;
    }

    public void remove(Long id) throws Exception{
        Medico medico = findById(id);
        medico.setStAtivo(false);
        medicoRepository.saveAndFlush(medico);
    }

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public Medico findById(Long id) throws Exception{
        Optional<Medico> retorno = medicoRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Medico com ID " + id + " Não Identificado");
    }

    public List<Medico> findByFilters(String nome) {

        return medicoRepository.findByNomeContainingAllIgnoringCase(nome);

    }

    public List<MedicoRepository.MedicoProjection> findAllOrder() {

        return medicoRepository.findAllByOrderByNomeAsc();

    }



}
