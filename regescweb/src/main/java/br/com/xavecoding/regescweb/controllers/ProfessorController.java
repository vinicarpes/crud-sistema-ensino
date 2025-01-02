package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisaoNovoProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index() {
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professores/new")
    public ModelAndView nnew(RequisaoNovoProfessor requisao) {
        ModelAndView mv = new ModelAndView("/professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        mv.addObject("requisicaoNovoProfessor", new RequisaoNovoProfessor()); // Instância necessária para o formulário
        return mv;
    }

    @PostMapping("/professores")
    public ModelAndView create(@Valid @ModelAttribute("requisicaoNovoProfessor") RequisaoNovoProfessor requisicao,
                               BindingResult result) {
        if (result.hasErrors()) {
            // Se houver erros, retorna a mesma página com os valores preenchidos e os erros
            ModelAndView mv = new ModelAndView("/professores/new");
            mv.addObject("statusProfessor", StatusProfessor.values());
            mv.addObject("requisicaoNovoProfessor", requisicao); // Preenche os dados do formulário
            return mv;
        } else {
            // Se não houver erros, salva o professor e redireciona
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);

            return new ModelAndView("redirect:/professores/" + professor.getId());
        }
    }

    @GetMapping("/professores/{id}")
    public ModelAndView show(@PathVariable Long id){ //define o Long id como referencia para o getmapping
        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()){
            Professor professor = optional.get();

            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);

            return mv;
        }else {
            return new ModelAndView("redirect:/professores");
        }

    }
}