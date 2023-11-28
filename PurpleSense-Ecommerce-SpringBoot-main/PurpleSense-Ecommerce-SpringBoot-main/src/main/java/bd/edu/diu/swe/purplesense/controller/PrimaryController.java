package bd.edu.diu.swe.purplesense.controller;

import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.service.PurpleSenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PrimaryController {

    @Autowired
    private PurpleSenseService purpleSenseService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (principal == null)
            return "redirect:/login";

        model.addAttribute("title", "PurpleSense Ecommerce");
        model.addAttribute("purpleSense", new PurpleSense());
        model.addAttribute("purpleSenses", purpleSenseService.getAll());
        return "index";
    }
}
