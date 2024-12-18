package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.dto.RequisaoNovoProfessor;
import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import br.com.xavecoding.regescweb.repositories.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

    @Controller
    public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;


    @GetMapping("/professores")
        public ModelAndView index(){

            List<Professor> professores = this.professorRepository.findAll(); //criando uma list de professores

            ModelAndView mv = new ModelAndView("professores/index");
            mv.addObject("professores", professores);
            return mv;
    }

    @GetMapping("/professores/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("/professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());

        return mv;
    }

    @PostMapping("/professores")
    public String create(@Valid @ModelAttribute("requisicaoNovoProfessor") RequisaoNovoProfessor requisicao, BindingResult result) {//anotação antes do parâmtro para impedir a inserção de valores conforme especificado na classe DTO
        System.out.println(requisicao);
        if (result.hasErrors()) {
            System.out.println("###########POSSUI ERROS#############");
            return "redirect:/professores/new";
        } else {
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);

            return "redirect:/professores";
        }
    }


}
