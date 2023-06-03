package br.com.unipar.TrabalhoWebSpring.controllers;

import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/endereco")
@Api(tags = "API Endereço", description = "Endereço")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ApiOperation(value = "Adicionar um endereço")
    public Endereco insert(@Valid @RequestBody Endereco endereco) throws Exception{

        return enderecoService.insert(endereco);
    }

    @PutMapping
    @ApiOperation(value = "Editar um endereço")
    public Endereco edit(@RequestBody Endereco endereco) throws Exception{

        return enderecoService.edit(endereco);


    }

    @GetMapping
    @ApiOperation(value = "Obter uma lista de endereços")
    public List<Endereco> findAll(){

        return enderecoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter um endereço pelo seu ID")
    public Endereco findById(@PathVariable Long id) throws Exception {

        return enderecoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Obter um endereço pelo seu logradouro")
    public List<Endereco> findByFilters(@RequestParam("logradouro") String logradouro){

        return enderecoService.findByFilters(logradouro);

    }
}
