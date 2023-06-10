package br.com.unipar.TrabalhoWebSpring.controllers;

import br.com.unipar.TrabalhoWebSpring.models.ConsultaCancelamento;
import br.com.unipar.TrabalhoWebSpring.models.dto.ConsultaCancelamentoDTO;
import br.com.unipar.TrabalhoWebSpring.services.ConsultaCancelamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/consultacancelamento")
@Api(tags = "API Consulta Cancelamento", description = "Consulta Cancelamento")
public class ConsultaCancelamentoController {

    @Autowired
    private ConsultaCancelamentoService consultaCancelamentoService;

    @PostMapping
    @ApiOperation(value = "Adicionar um Cancelamento de consulta")
    public ConsultaCancelamento insert(@Valid @RequestBody ConsultaCancelamentoDTO request) throws Exception{

        return consultaCancelamentoService.insert(request);
    }

    @PutMapping
    @ApiOperation(value = "Editar um Cancelamento de consulta")
    public ConsultaCancelamento edit(@RequestBody ConsultaCancelamento consultaCancelamento) throws Exception{
        return consultaCancelamentoService.edit(consultaCancelamento);
    }

    @GetMapping
    @ApiOperation(value = "Obter uma lista de Cancelamento de consultas")
    public List<ConsultaCancelamento> findAll(){

        return consultaCancelamentoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um cancelamento de consulta pelo seu ID")
    public ConsultaCancelamento findById(@PathVariable Long id) throws Exception {

        return consultaCancelamentoService.findById(id);
    }
}