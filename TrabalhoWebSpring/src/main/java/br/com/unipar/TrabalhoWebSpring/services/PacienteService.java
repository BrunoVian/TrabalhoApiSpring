package br.com.unipar.TrabalhoWebSpring.services;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import br.com.unipar.TrabalhoWebSpring.models.dto.PacienteEditDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.EnderecoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Paciente insert(Paciente paciente) throws Exception{

        pacienteRepository.saveAndFlush(paciente);

        return paciente;
    }

    public Paciente edit(Long id, PacienteEditDTO request) throws Exception {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id: " + id + " não encontrado"));

        paciente.setNome(request.getNome());
        paciente.setTelefone(request.getTelefone());

        Endereco endereco = enderecoRepository.findById(request.getEnderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço com id: " + request.getEnderecoId() + " não encontrado"));

        paciente.setEndereco(endereco);

        return pacienteRepository.save(paciente);
    }


    public void remove(Long id) throws Exception{
        Paciente paciente = findById(id);
        paciente.setStAtivo(false);
        pacienteRepository.saveAndFlush(paciente);
    }

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Paciente findById(Long id) throws Exception{
        Optional<Paciente> retorno = pacienteRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Paciente com ID " + id + " Não Identificado");
    }

    public List<Paciente> findByFilters(String nome) {

        return pacienteRepository.findByNomeContainingAllIgnoringCase(nome);

    }

    public List<PacienteRepository.PacienteProjection> findAllOrder() {

        return pacienteRepository.findAllByOrderByNomeAsc();

    }


}
