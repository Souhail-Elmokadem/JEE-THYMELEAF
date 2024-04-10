package com.AtelierJEE.JEEVIDEOCREATOR.web;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import com.AtelierJEE.JEEVIDEOCREATOR.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/creator")
public class CreatorController {
    @Autowired
    CreatorService Cservice;

    @GetMapping("/allCreators")
    public String getAllCreators(Model model,
                                 @RequestParam(name = "Search", defaultValue = "")String kw,
                                 @RequestParam(name = "size" , defaultValue = "5")int size,
                                 @RequestParam(name = "page", defaultValue = "0") int page){
        Page<Creator> Pagecreators = Cservice.findCreatorByUsername(kw, page, size);
        model.addAttribute("creators",Pagecreators.getContent());
        System.out.println("size"+Pagecreators.getContent().size());
        System.out.println("---------------------------");
        System.out.println(Pagecreators.getTotalPages());
        System.out.println("---------------------------");
        model.addAttribute("pages",new int[Pagecreators.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "allcreators";
    }

    @GetMapping("/register")
    public String registerAsCreator(Model model){
        return "RegisterCreator";
    }

    @PostMapping("register")
    public String registerAction(Model model, @RequestParam(name = "name")
                                 String name,
                                 @RequestParam(name = "email")
                                 String email,
                                 @RequestParam(name = "password")
                                 String Password){
        Creator creator = new Creator();
        creator.setEmail(email);
        creator.setName(name);
        creator.setPassword(Password);
        creator.setCreatedAt(new Date());
        Cservice.AddCreator(creator);
        return "redirect:/creator/allCreators";
    }
    @GetMapping("/UpdateViewCreator/{id}")
    public String updateCreatorView(@PathVariable("id") Long id,Model model){
        model.addAttribute("creator", Cservice.getCreator(id));
        model.addAttribute("modelCreator",new Creator());
        return "updateCreator";
    }

    @PostMapping("/updateCreator/{id}")
    public String updateCreator(Long id,@ModelAttribute("creator")  Creator creator){
        Cservice.ModifyCreator(id,creator);
        return "redirect:/creator/allCreators";
    }

    @GetMapping("/login")
    public String Loginform(Model model){
        return "login";
    }

    @PostMapping("login")
    public String loginAction(Model model,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "password") String password){
        if(Cservice.findCreator(email).getPassword().equals(password)){
            System.out.println(Cservice.findCreator(email));
            return "redirect:/creator/allCreators";
        }
        else
            return Loginform(model);
    }

    @GetMapping("/delete/{id}")
    public String DeleteCreator(@PathVariable Long id){
        Cservice.DeleteCreator(id);
        return "redirect:/creator/allCreators";
    }

}
