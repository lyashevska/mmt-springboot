package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.entity.Manuscript;
import nl.lyashevska.mmtspringboot.service.ManService;
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
public class ManController {

    @Autowired
    private ManService service;

    @GetMapping("/")
    public String home(Model m) {
        List<Manuscript> man = service.getAllMan();
        m.addAttribute("man", man);
        return "index";
    }

    @GetMapping("/addman")
    public String addManForm() {
        return "add_man";
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
        return "redirect:/";
    }

    // post is used to add new resources
    @PostMapping("/register")
    public String manRegister(@ModelAttribute Manuscript m, HttpSession session) {
        System.out.println(m);
        service.addMan(m);
        session.setAttribute("msg", "Manuscript added successfully");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateMan(@ModelAttribute Manuscript m, HttpSession session) {
        service.addMan(m);
        session.setAttribute("msg", "Manuscript updated successfully");
        return "redirect:/";
    }

}
