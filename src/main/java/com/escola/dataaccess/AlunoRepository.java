package com.escola.dataaccess;

import com.escola.entities.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno,Integer> {
}
