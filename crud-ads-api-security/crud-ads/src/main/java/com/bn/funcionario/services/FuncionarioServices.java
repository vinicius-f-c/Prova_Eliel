package com.bn.funcionario.services;

import com.bn.funcionario.models.FuncionarioModel;
import com.bn.funcionario.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioServices {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionário não encontrado: " + id));
    }

    public FuncionarioModel criarPessoa(FuncionarioModel funcionarioModel) {
        funcionarioModel.setId(null); // garante que será um INSERT
        return funcionarioRepository.save(funcionarioModel);
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Funcionário não encontrado: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioModel atualizar(Long id, FuncionarioModel funcionarioModel) {
        FuncionarioModel func = buscarPorId(id);
        func.setNome(funcionarioModel.getNome());
        func.setCpf(funcionarioModel.getCpf());
        func.setRua(funcionarioModel.getRua());
        return funcionarioRepository.save(func);
    }
}
