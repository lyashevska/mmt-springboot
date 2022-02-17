package nl.lyashevska.mmtspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
}
