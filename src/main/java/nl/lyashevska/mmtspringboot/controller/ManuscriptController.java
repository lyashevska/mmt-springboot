/**
 * The controller is responsible for connecting the backend services to the front end Thymeleaf template.
 * https://wkrzywiec.medium.com/full-text-search-with-hibernate-search-lucene-part-1-e245b889aa8e
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

@Controller
public class ManuscriptController {

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
            List<Manuscript> man = manuscriptService.getAllManuscript();
            m.addAttribute("man", man);
            return "afterlogin";
        }

        // show only records matching search
        if (searchText != null) {
            m.addAttribute("man", searchService.getManuscriptAuthor(searchText));
        }
        // FIX returns index!
        return "redirect:/afterlogin";
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

    // TODO --> move under edit?
//    @GetMapping("/upload/{id}")
//    public String uploadFile(@PathVariable int id,
//                             Model mod,
//                             @RequestParam("content") MultipartFile multipartFile,
//                             RedirectAttributes ra) throws Exception {
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//
//        try{
//            if(fileName.contains("..")){
//                throw new Exception("Filename contains invalid path sequence" + fileName);
//            }
//
////        Manuscript content = new Manuscript();
//            Manuscript content = service.getManuscriptById(id);
//            content.setContent(multipartFile.getBytes());
////        m.setSize(multipartFile.getSize());
////         manuscriptRepository.save(content);
//            mod.addAttribute("man", content);
//            ra.addFlashAttribute("message", "The file has been uploaded.");
//            return "redirect:/afterlogin";
//
//        } catch (Exception e) {
//            throw new Exception("Could not save File: " + fileName);
//
//        }
//    }

//    @PostMapping("/upload")
//    public String uploadFile(Manuscript m,
//             @RequestParam("file") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
//        String fileName = multipartFile.getOriginalFilename();
//        Manuscript m = new Manuscript();
//        m.setContent(multipartFile.getBytes());
//        m.setSize(multipartFile.getSize());
//        manuscriptRepository.save(m);
////        m.addAttribute("man", m);
//        ra.addFlashAttribute("message", "The file has been uploaded.");
//        return "redirect:/afterlogin";
//    }


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

    // add download

}
