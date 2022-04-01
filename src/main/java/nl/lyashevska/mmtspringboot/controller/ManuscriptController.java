package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.service.ManuscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManuscriptController {

    @Autowired
    private ManuscriptService service;

    @GetMapping("/")
    public String home(Model m) {
        List<Manuscript> man = service.getAllMan();
        m.addAttribute("man", man);
        return "index";
    }
// home
    @GetMapping("/afterlogin")
    public String afterlogin(Model m) {
        List<Manuscript> man = service.getAllMan();
        m.addAttribute("man", man);
        return "afterlogin";
    }

    @GetMapping("/login")
    public String login(){
//        return "redirect:/afterlogin";
        return "login";
    }

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model mod) {
        Manuscript m = service.getManById(id);
        mod.addAttribute("man", m);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteMan(@PathVariable int id, HttpSession session) {
        service.deleteMan(id);
        session.setAttribute("msg", "Record deleted successfully");
        return "redirect:/afterlogin";
    }

    // post is used to add new resources
    @PostMapping("/register")
    public String manRegister(@ModelAttribute Manuscript m, HttpSession session) {
        System.out.println(m);
        service.add(m);
        session.setAttribute("msg", "Manuscript added successfully");
        return "redirect:/afterlogin";
    }

    @PostMapping("/update")
    public String updateMan(@ModelAttribute Manuscript m, HttpSession session) {
        service.add(m);
        session.setAttribute("msg", "Manuscript updated successfully");
        return "redirect:/afterlogin";
    }

}
