package br.com.unipar.TrabalhoWebSpring.controllers;

import br.com.unipar.TrabalhoWebSpring.models.Medico;
import br.com.unipar.TrabalhoWebSpring.repositories.MedicoRepository;
import br.com.unipar.TrabalhoWebSpring.repositories.PacienteRepository;
import br.com.unipar.TrabalhoWebSpring.services.MedicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/medico")
@Api(tags = "API Medico", description = "Medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @ApiOperation(value = "Adicionar um medico")
    public Medico insert(@RequestBody Medico medico) throws Exception{

        return medicoService.insert(medico);
    }

    @PutMapping
    @ApiOperation( value = "Editar um medico")
    public Medico edit(@RequestBody Medico medico) throws Exception{

        return medicoService.edit(medico);

    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Excluir um medico")
    public void delete(@PathVariable Long id) throws Exception {
        medicoService.remove(id);
    }



    @GetMapping
    @ApiOperation(value = "Obter uma lista de medicos")
    public List<Medico> findAll(){

        return medicoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um medico pelo seu ID")
    public Medico findById(@PathVariable Long id) throws Exception {

        return medicoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um medico pelo seu nome")
    public List<Medico> findByFilters(@RequestParam("nome") String nome){

        return medicoService.findByFilters(nome);
    }

    @GetMapping(path = "/order")
    @ApiOperation(value = "Obter uma lista de medicos ordenados pelo nome")
    public List<MedicoRepository.MedicoProjection> findByFilters(){

        return medicoService.findAllOrder();
    }

}
