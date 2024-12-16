package br.com.xavecoding.regescweb.repositories;

import br.com.xavecoding.regescweb.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> { //passa o tipo de dados dos repositorio e o tipo de dado da chave primaria do mesmo

}
