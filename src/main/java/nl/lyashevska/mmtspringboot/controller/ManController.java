package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.entity.Manuscript;
import nl.lyashevska.mmtspringboot.service.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ManController {

    @Autowired
    private ManService service;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/addman")
    public String addManForm(){
        return "add_man";
    }

    @PostMapping("/register")
    public String manRegister(@ModelAttribute Manuscript m, HttpSession session){
        System.out.println(m);
        service.addMan(m);
        session.setAttribute("msg", "Manuscript added sucessfully");
        return "redirect:/";
    }
}
