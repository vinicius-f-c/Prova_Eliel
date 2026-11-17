package com.bn.funcionario.controllers;

import com.bn.funcionario.models.FuncionarioModel;
import com.bn.funcionario.services.FuncionarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioServices funcionarioServices;


    @GetMapping
    public List<FuncionarioModel> findAll() {
        return funcionarioServices.findAll();
    }


    @GetMapping("/{id}")
    public FuncionarioModel buscarPorId(@PathVariable Long id) {
        return funcionarioServices.buscarPorId(id);
    }


    @PostMapping
    public ResponseEntity<FuncionarioModel> criarFuncionario(@RequestBody FuncionarioModel funcionarioModel) {
        FuncionarioModel criado = funcionarioServices.criarPessoa(funcionarioModel);
        return ResponseEntity.created(URI.create("/api/funcionarios/" + criado.getId())).body(criado);
    }


    @PutMapping("/{id}")
    public FuncionarioModel atualizar(@PathVariable Long id, @RequestBody FuncionarioModel funcionarioModel) {
        return funcionarioServices.atualizar(id, funcionarioModel);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        funcionarioServices.deletar(id);
    }
}
