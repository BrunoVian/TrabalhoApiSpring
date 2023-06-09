package br.com.unipar.TrabalhoWebSpring.controllers;

import br.com.unipar.TrabalhoWebSpring.models.Consulta;
import br.com.unipar.TrabalhoWebSpring.models.Paciente;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaDTO;
import br.com.unipar.TrabalhoWebSpring.models.dto.PacienteEditDTO;
import br.com.unipar.TrabalhoWebSpring.services.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(path = "/consulta")
@Api(tags = "API Consulta", description = "Consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @ApiOperation(value = "Adicionar uma consulta")
    public Consulta insert(@Valid @RequestBody ConsultaDTO request) throws Exception{

        Consulta insertConsulta = consultaService.insert(request);

        return insertConsulta;
    }

    @PutMapping
    @ApiOperation(value = "Editar uma consulta")
    public Consulta edit(@RequestBody ConsultaDTO request) throws Exception{

        Consulta editConsulta = consultaService.edit(request);

        return editConsulta;

    }

    @GetMapping
    @ApiOperation(value = "Obter uma lista de consultas")
    public List<Consulta> findAll(){

        return consultaService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter uma consulta pelo seu ID")
    public Consulta findById(@PathVariable Long id) throws Exception {

        return consultaService.findById(id);
    }

}
