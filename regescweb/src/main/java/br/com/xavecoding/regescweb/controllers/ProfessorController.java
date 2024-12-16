package br.com.xavecoding.regescweb.controllers;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
    public class ProfessorController {
        @GetMapping("/professores")
        public ModelAndView index(){
            Professor batman = new Professor("Batman", new BigDecimal(5000), StatusProfessor.ATIVO);
            Professor coringa = new Professor("Coringa", new BigDecimal(50), StatusProfessor.APOSEENTADO);
            Professor wonderWoman = new Professor("Mulher Maravilha", new BigDecimal(45000), StatusProfessor.INATIVO);

            List<Professor> professores = Arrays.asList(batman, coringa, wonderWoman); //criando uma list de professores

            ModelAndView mv = new ModelAndView("professores/index");
            mv.addObject("professores", professores);
            return mv;
    }

}
