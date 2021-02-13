package repository;

import java.util.List;
import java.util.Optional;

import domain.Subcription;

public interface SubcriptionRepository {

    public Subcription save(Subcription subcription);

    public void delete(Long id);

    public Optional<Subcription> get(Long id);

    public List<Subcription> getAll();
    
}
