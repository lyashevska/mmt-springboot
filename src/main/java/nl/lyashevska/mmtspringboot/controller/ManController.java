package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.entity.Manuscript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/addman")
    public String addManForm(){
        return "add_man";
    }

    @PostMapping("/register")
    public String manRegister(@ModelAttribute Manuscript m){
        System.out.println(m);
        return "add_man";
    }
}
