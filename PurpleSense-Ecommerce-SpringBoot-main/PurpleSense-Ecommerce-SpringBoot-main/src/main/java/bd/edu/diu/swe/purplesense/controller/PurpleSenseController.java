package bd.edu.diu.swe.purplesense.controller;

import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.service.PurpleSenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class PurpleSenseController {
    @Autowired
    private PurpleSenseService purpleSenseService;

    @PostMapping("/purpleSense/new")
    public String purpleSenseSave(@ModelAttribute("purpleSense") PurpleSense purpleSense,
                              RedirectAttributes attributes) {
        if (purpleSense.getName() == null || purpleSense.getPrice() == 0 || purpleSense.getImage() == null || purpleSense.getCategory() == null) {
            attributes.addFlashAttribute("error", "Value cannot be null");
            return "redirect:/";
        }

        purpleSenseService.save(purpleSense);
        attributes.addFlashAttribute("success", "PurpleSense Successfully Added");
        return "redirect:/";
    }

    @GetMapping("/purpleSense/edit/{id}")
    public String purpleSenseEdit(@PathVariable String id,
                              Model model, Principal principal) {

        if (principal == null) return "redirect:/login";

        PurpleSense purpleSense = purpleSenseService.get(Long.parseLong(id));
        model.addAttribute("purpleSenseEdit", purpleSense);
        model.addAttribute("purpleSense", new PurpleSense());

        return "edit_purpleSense";
    }

    @PostMapping("/purpleSense/edit/save")
    public String purpleSenseEditSave(@ModelAttribute("purpleSenseEdit") PurpleSense purpleSenseEdit,
                                  Principal principal, RedirectAttributes attributes) {
        if (principal == null) return "redirect:/login";

        PurpleSense purpleSense = purpleSenseService.get(purpleSenseEdit.getId());

        purpleSense.setCategory(purpleSenseEdit.getCategory());
        purpleSense.setName(purpleSenseEdit.getName());
        purpleSense.setImage(purpleSenseEdit.getImage());
        purpleSense.setDescription(purpleSenseEdit.getDescription());
        purpleSense.setPrice(purpleSenseEdit.getPrice());

        purpleSenseService.save(purpleSense);
        attributes.addFlashAttribute("success", "PurpleSense edit successful");
        return "redirect:/";
    }


    @GetMapping("/purpleSense/delete/{id}")
    public String purpleSenseEdit(@PathVariable String id,
                              Model model, Principal principal,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";
        purpleSenseService.delete(Long.parseLong(id));
        attributes.addFlashAttribute("success", "PurpleSense delete successful");
        return "redirect:/";
    }
}
