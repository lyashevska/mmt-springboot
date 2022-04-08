/**
 * The controller is responsible for connecting the backend services to the front end Thymeleaf template.
 * https://wkrzywiec.medium.com/full-text-search-with-hibernate-search-lucene-part-1-e245b889aa8e
 */
package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.service.ManuscriptService;
import nl.lyashevska.mmtspringboot.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManuscriptController {

    // inject the SearchService object
    @Autowired
    private SearchService searchService;

    // inject ManuscriptService
    @Autowired
    private ManuscriptService service;

    // home with the search bar
    @GetMapping("/")
    public String home(Model m, @RequestParam(value = "search", required = false) String searchText) {
        // show all records
        if (searchText == null) {
            List<Manuscript> man = service.getAllManuscript();
            m.addAttribute("man", man);
            return "index";
        }
        // show only records matching search
        if (searchText != null) {
            m.addAttribute("man", searchService.getManuscriptAuthor(searchText));
        }
        return "index";
    }

//        @GetMapping("/")
//    public String home(Model m) {
//        List<Manuscript> man = service.getAllManuscript();
//        m.addAttribute("man", man);
//        return "index";
//    }

    //afterlogin with the search bar
    @GetMapping("/afterlogin")
    public String afterlogin(Model m, @RequestParam(value = "search", required = false) String searchText) {
        // show all records
        if (searchText == null) {
            List<Manuscript> man = service.getAllManuscript();
            m.addAttribute("man", man);
            return "afterlogin";
        }
        // show only records matching search
        if (searchText != null) {
            m.addAttribute("man", searchService.getManuscriptAuthor(searchText));
        }
        return "afterlogin";
    }

//    @GetMapping("/afterlogin")
//    public String afterlogin(Model m) {
//        List<Manuscript> man = service.getAllManuscript();
//        m.addAttribute("man", man);
//        return "afterlogin";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model mod) {
        Manuscript m = service.getManuscriptById(id);
        mod.addAttribute("man", m);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteManuscript(@PathVariable int id, HttpSession session) {
        service.deleteManuscript(id);
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
