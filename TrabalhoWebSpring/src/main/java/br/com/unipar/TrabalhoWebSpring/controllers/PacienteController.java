package br.com.unipar.TrabalhoWebSpring.controllers;


import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import br.com.unipar.TrabalhoWebSpring.models.dto.PacienteEditDTO;
import br.com.unipar.TrabalhoWebSpring.repositories.PacienteRepository;
import br.com.unipar.TrabalhoWebSpring.services.PacienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/paciente")
@Api(tags = "API Paciente", description = "Paciente")
public class PacienteController {


    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @ApiOperation(value = "Adicionar um paciente")
    public Paciente insert(@Valid @RequestBody Paciente paciente) throws Exception{

        return pacienteService.insert(paciente);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation("Editar os dados de um paciente")
    public Paciente edit(@PathVariable Long id, @RequestBody @Valid PacienteEditDTO request) throws Exception {

        Paciente updatedPaciente = pacienteService.edit(id, request);

        return updatedPaciente;
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Excluir um paciente")
    public void delete(@PathVariable Long id) throws Exception {
        pacienteService.remove(id);
    }



    @GetMapping
    @ApiOperation(value = "Obter uma lista de pacintes")
    public List<Paciente> findAll(){

        return pacienteService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um paciente pelo seu ID")
    public Paciente findById(@PathVariable Long id) throws Exception {

        return pacienteService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um paciente pelo seu nome")
    public List<Paciente> findByFilters(@RequestParam("nome") String nome){

        return pacienteService.findByFilters(nome);
    }

    @GetMapping(path = "/order")
    @ApiOperation(value = "Obter uma lista de pacientes ordenadas pelo nome")
    public List<PacienteRepository.PacienteProjection> findByFilters(){

        return pacienteService.findAllOrder();
    }

}
