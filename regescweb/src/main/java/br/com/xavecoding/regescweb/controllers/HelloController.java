package br.com.xavecoding.regescweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @GetMapping("/hello-modelAndView")//mapeia o metodo
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView("hello"); // renderiza o html com o nome do valor passado
        mv.addObject("nome", "Ulysses");
        return mv;//o spring vai renderizar o arquivo que está em templates
    }

    @GetMapping("/hello-model")//mapeia o metodo
    public String hello(Model model){
        model.addAttribute("nome", "Vinicius");
        return "hello";//o spring vai renderizar o arquivo que está em templates
    }
    @GetMapping("/hello-servlet")//mapeia o metodo
    public String hello(HttpServletRequest request){
        request.setAttribute("nome", "Juan");
        return "hello";//o spring vai renderizar o arquivo que está em templates
    }



}
