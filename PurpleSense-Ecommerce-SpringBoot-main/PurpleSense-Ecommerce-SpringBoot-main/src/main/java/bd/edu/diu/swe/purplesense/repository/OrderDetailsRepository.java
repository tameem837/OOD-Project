package bd.edu.diu.swe.purplesense.repository;

import bd.edu.diu.swe.purplesense.model.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
}
