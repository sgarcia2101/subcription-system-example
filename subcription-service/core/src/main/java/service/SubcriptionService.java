package service;

import java.util.List;
import java.util.Optional;

import domain.Subcription;


public interface SubcriptionService {
	
    Subcription save(Subcription subcription);

    void delete(Long id);

    Optional<Subcription> get(Long id);

    List<Subcription> getAll();

}
