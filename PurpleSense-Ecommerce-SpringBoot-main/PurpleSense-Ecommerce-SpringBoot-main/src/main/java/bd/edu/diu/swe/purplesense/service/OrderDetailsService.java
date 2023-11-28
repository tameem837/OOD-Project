package bd.edu.diu.swe.purplesense.service;

import bd.edu.diu.swe.purplesense.model.Cart;
import bd.edu.diu.swe.purplesense.model.Orders;
import bd.edu.diu.swe.purplesense.model.OrderDetails;
import bd.edu.diu.swe.purplesense.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private CartService cartService;

    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public void saveBulk(List<Cart> carts, Orders order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrders(order);
        for (Cart cart : carts) {
            orderDetails.setPrice(cart.getPurpleSense().getPrice());
            orderDetails.setPurpleSense(cart.getPurpleSense());
            orderDetails.setQuantity(cart.getQuantity());

            cartService.deleteCart(cart);
            save(orderDetails);
        }
    }
}
