package com.sgarcia.subcription.repository;

import java.util.List;
import java.util.Optional;

import com.sgarcia.subcription.domain.Subcription;

public interface SubcriptionRepository {

    public Subcription save(Subcription subcription);

    public void delete(Long id);

    public Optional<Subcription> get(Long id);

    public List<Subcription> getAll();
    
}
