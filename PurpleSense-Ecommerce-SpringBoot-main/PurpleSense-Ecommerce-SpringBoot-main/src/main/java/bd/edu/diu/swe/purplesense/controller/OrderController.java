package bd.edu.diu.swe.purplesense.controller;

import bd.edu.diu.swe.purplesense.model.Cart;
import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.model.Orders;
import bd.edu.diu.swe.purplesense.service.CartService;
import bd.edu.diu.swe.purplesense.service.OrderDetailsService;
import bd.edu.diu.swe.purplesense.service.OrderService;
import bd.edu.diu.swe.purplesense.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDetailsServiceImplement userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderDetailsService orderDetailsService;


    @GetMapping("/checkout")
    private String checkout(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        model.addAttribute("purpleSense", new PurpleSense());
        return "checkout";
    }

    @PostMapping("/place-order")
    private String placeOrder(Principal principal,
                              @RequestParam("address") String address,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";

        Orders order = new Orders();
        order.setAddress(address);
        order.setUser(userService.getByUserEmail(principal.getName()));
        order.setDate(new Date());
        order.setPayment(true);
        order.setPaymentType("COD");

        List<Cart> carts = cartService.listAll(principal.getName());

        double totalPrice = carts.stream()
                .mapToDouble(cart -> cart.getPurpleSense().getPrice() * cart.getQuantity())
                .sum();

        order.setTotalPrice(totalPrice);
        orderService.save(order);
        orderDetailsService.saveBulk(carts, order);
        attributes.addFlashAttribute("success", "Order Placed Successfully");

        return "redirect:/";
    }
}
