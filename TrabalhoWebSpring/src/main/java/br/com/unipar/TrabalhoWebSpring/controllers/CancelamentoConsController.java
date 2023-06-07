package br.com.unipar.TrabalhoWebSpring.controllers;

import br.com.unipar.TrabalhoWebSpring.models.ConsultaCanc;
import br.com.unipar.TrabalhoWebSpring.services.CancelamentoConsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/consultaCanc")
@Api(tags = "API Consulta", description = "Consulta")
public class CancelamentoConsController {

    @Autowired
    private CancelamentoConsService consultaCanService;

    @PostMapping
    @ApiOperation(value = "Adicionar um Cancelamento de consulta")
    public ConsultaCanc insert(@Valid @RequestBody ConsultaCanc consultaCanc) throws Exception{

        return consultaCanService.insert(consultaCanc);
    }

    @PutMapping
    @ApiOperation(value = "Editar um Cancelamento de consulta")
    public ConsultaCanc edit(@RequestBody ConsultaCanc consultaCanc) throws Exception{

        return consultaCanService.edit(consultaCanc);

    }

    @GetMapping
    @ApiOperation(value = "Obter uma lista de Cancelamento de consultas")
    public List<ConsultaCanc> findAll(){

        return consultaCanService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um cancelamento de consulta pelo seu ID")
    public ConsultaCanc findById(@PathVariable Long id) throws Exception {

        return consultaCanService.findById(id);
    }
}
