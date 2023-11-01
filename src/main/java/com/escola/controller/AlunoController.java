package com.escola.controller;

import com.escola.dataaccess.AlunoRepository;
import com.escola.entities.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@Controller
@RequestMapping(path="/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Aluno> getAll() {
        return repository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestBody Aluno aluno) {
        repository.save(aluno);
        return "Salvo com sucesso";
    }
    @GetMapping(path = "/matricula/{id}")
    public @ResponseBody Aluno getById(@PathVariable("id") Integer matricula) {
        return repository.findById(matricula).get();
    }
    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable("id") Integer matricula) {
        repository.deleteById(matricula);
        return "Deletado com sucesso";
    }

    @PutMapping(path = "/{id}")
    public String put(@RequestBody Aluno newAluno, @PathVariable Integer id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setNome(newAluno.getNome());
                    employee.setSala(newAluno.getSala());
                    employee.setIdade(newAluno.getIdade());
                    repository.save(employee);
                    return "Aluni atualizad";
                })
                .orElseGet(() -> {
                    newAluno.setMatricula(id);
                    repository.save(newAluno);
                    return "Aluno Inserido";
                });
    }
}
