package bd.edu.diu.swe.purplesense.service;

import bd.edu.diu.swe.purplesense.model.Orders;
import bd.edu.diu.swe.purplesense.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(Orders order) {
        orderRepository.save(order);
    }
}
