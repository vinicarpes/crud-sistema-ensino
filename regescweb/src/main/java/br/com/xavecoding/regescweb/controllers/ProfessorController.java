package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisicaoFormProfessor;
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
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index() {
        List<Professor> professores = professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor requisao) {
        ModelAndView mv = new ModelAndView("/professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        mv.addObject("requisicaoNovoProfessor", new RequisicaoFormProfessor()); // Instância necessária para o formulário
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid @ModelAttribute("requisicaoFormProfessor") RequisicaoFormProfessor requisicao,
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

    @GetMapping("/{id}")
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

    @GetMapping("/{id}/edit")
    public  ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao){
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()){
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);

            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("requisaoFormProfessor", requisicao); //adiciona o DTO ao modelo
            mv.addObject("statusProfessor", StatusProfessor.values());
            mv.addObject("professorId", professor.getId());
            return mv;
        }else {
            return new ModelAndView("redirect:/professores");
        }


    }

}