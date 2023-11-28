package bd.edu.diu.swe.purplesense.service;

import bd.edu.diu.swe.purplesense.model.Cart;
import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.model.User;
import bd.edu.diu.swe.purplesense.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> listAll(String username) {
        return (List<Cart>) cartRepository.findCartsByUserUsername(username);
    }

    public Cart get(long id) {
        return cartRepository.findCartById(id);
    }

    public void addToCart(PurpleSense purpleSense, User user) {

        Cart cart = null;

        cart = cartRepository.findCartByPurpleSenseIdAndUserId(purpleSense.getId(), user.getId());

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setPurpleSense(purpleSense);
            cart.setQuantity(1);
        } else {
            cart.setQuantity(cart.getQuantity()+1);
        }

        cartRepository.save(cart);
    }

    public void updateCart(long cartId, int quantity) {
        Cart cart = getById(cartId);
        cart.setQuantity(quantity);

        cartRepository.save(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    Cart getById(long id) {
        return cartRepository.findCartById(id);
    }
}
