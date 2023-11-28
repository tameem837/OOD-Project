package bd.edu.diu.swe.purplesense.repository;

import bd.edu.diu.swe.purplesense.model.PurpleSense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurpleSenseRepository extends CrudRepository<PurpleSense, Long> {
    PurpleSense getPurpleSenseById(long id);
}
