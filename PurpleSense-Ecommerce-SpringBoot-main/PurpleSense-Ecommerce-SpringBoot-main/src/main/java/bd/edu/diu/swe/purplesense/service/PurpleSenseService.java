package bd.edu.diu.swe.purplesense.service;

import bd.edu.diu.swe.purplesense.model.PurpleSense;
import bd.edu.diu.swe.purplesense.repository.PurpleSenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurpleSenseService {
    @Autowired
    private PurpleSenseRepository purpleSenseRepository;

    public List<PurpleSense> getAll() {
        return (List<PurpleSense>) purpleSenseRepository.findAll();
    }

    public PurpleSense get(long id) {
        return purpleSenseRepository.getPurpleSenseById(id);
    }

    public void save(PurpleSense purpleSense) {
        purpleSenseRepository.save(purpleSense);
    }

    public void delete(long id) {
        purpleSenseRepository.delete(get(id));
    }

}
