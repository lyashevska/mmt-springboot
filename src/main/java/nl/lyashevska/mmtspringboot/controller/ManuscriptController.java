/**
 * The controller is responsible for connecting the backend services to the front end Thymeleaf template.
 * https://wkrzywiec.medium.com/full-text-search-with-hibernate-search-lucene-part-1-e245b889aa8e
 * https://www.bezkoder.com/spring-boot-upload-file-database/
 *
 */

package nl.lyashevska.mmtspringboot.controller;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import nl.lyashevska.mmtspringboot.service.ManuscriptService;
import nl.lyashevska.mmtspringboot.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// The controller is a component of the web app that contains methods (often named actions)
// executed for a specific HTTP request
@Controller
public class ManuscriptController {

    // instruct Spring to provide a BEan from its context and set it directly as the value of the field
    // this way a relationship between the two beans is established
    // inject the SearchService object
    @Autowired
    private SearchService searchService;

    // inject ManuscriptService
    @Autowired
    private ManuscriptService manuscriptService;

    // add repository
    @Autowired
    private ManuscriptRepository manuscriptRepository;

    // home with the search bar
    @GetMapping("/")
    public String home(Model m, @RequestParam(value = "search", required = false) String searchText) {
        // show all records
        if (searchText == null) {
            List<Manuscript> man = manuscriptService.getAllManuscript();
            m.addAttribute("man", man);
            return "index";
        }
        // show only records matching search
        if (searchText != null) {
            m.addAttribute("man", searchService.getManuscriptAuthor(searchText));
        }
        return "index";
    }

    //afterlogin with the search bar
    @GetMapping("/afterlogin")
    public String afterlogin(Model m, @RequestParam(value = "search", required = false) String searchText) {
        // show all records
        if (searchText == null) {
            List<Manuscript> man = manuscriptService.getAllManuscript();
            m.addAttribute("man", man);
            return "afterlogin";
        }

        // show only records matching search
        if (searchText != null) {
            m.addAttribute("man", searchService.getManuscriptAuthor(searchText));
        }
//        return "redirect:/afterlogin";
        return "afterlogin";
    }

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
        Manuscript m = manuscriptService.getManuscriptById(id);
        mod.addAttribute("man", m);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteManuscript(@PathVariable int id, HttpSession session) {
        manuscriptService.deleteManuscript(id);
        session.setAttribute("msg", "Record deleted successfully");
        return "redirect:/afterlogin";
    }

    // post is used to add new resources
    @PostMapping("/register")
    public String manRegister(@ModelAttribute Manuscript m, HttpSession session) {
        System.out.println(m);
        manuscriptService.add(m);
        session.setAttribute("msg", "Manuscript added successfully");
        return "redirect:/afterlogin";
    }

    @PostMapping("/update")
    public String updateMan(@ModelAttribute Manuscript m, HttpSession session) {
        manuscriptService.add(m);
        session.setAttribute("msg", "Manuscript updated successfully");
        return "redirect:/afterlogin";
    }

}
