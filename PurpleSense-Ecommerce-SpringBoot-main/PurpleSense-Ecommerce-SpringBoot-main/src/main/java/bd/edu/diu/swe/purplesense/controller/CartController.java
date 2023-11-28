package bd.edu.diu.swe.purplesense.controller;

import bd.edu.diu.swe.purplesense.model.Cart;
import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.model.User;
import bd.edu.diu.swe.purplesense.service.CartService;
import bd.edu.diu.swe.purplesense.service.PurpleSenseService;
import bd.edu.diu.swe.purplesense.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private UserDetailsServiceImplement userService;

    @Autowired
    private PurpleSenseService purpleSenseService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model,
                       Principal principal) {

        if (principal == null) return "redirect:/login";

        List<Cart> carts = cartService.listAll(principal.getName());

        double totalPrice = carts.stream()
                .mapToDouble(cart -> cart.getPurpleSense().getPrice() * cart.getQuantity())
                .sum();

        model.addAttribute("carts", carts);
        model.addAttribute("title", "Cart");
        model.addAttribute("purpleSense", new PurpleSense());
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }


    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long purpleSenseId,
            Principal principal,
            RedirectAttributes attributes){

        if (principal == null) return "redirect:/login";

        PurpleSense purpleSense = purpleSenseService.get(purpleSenseId);
        User user = userService.getByUserEmail(principal.getName());
        cartService.addToCart(purpleSense, user);

        attributes.addFlashAttribute("success", "PurpleSense added successfully");
        return "redirect:/cart";
    }

    @GetMapping(value = "/delete-cart/{id}")
    public String deleteItemFromCart(Principal principal, @PathVariable String id){
        if(principal == null){
            return "redirect:/login";
        }else{
            cartService.deleteCart(cartService.get(Long.parseLong(id)));
            return "redirect:/cart";
        }
    }

    @PostMapping(value = "/update-cart")
    public String updateItemFromCart(@RequestParam("id") Long cartId,
                                     @RequestParam("quantity") int quantity,
                                     Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            cartService.updateCart(cartId, quantity);
            return "redirect:/cart";
        }
    }
}
