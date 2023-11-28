package bd.edu.diu.swe.purplesense.repository;

import bd.edu.diu.swe.purplesense.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findCartById(long id);
    List<Cart> findCartsByUserUsername(String username);
    Cart findCartByPurpleSenseIdAndUserId(long productId, long userId);
}
