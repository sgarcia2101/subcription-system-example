package com.sgarcia.subcription.service;

import java.util.List;
import java.util.Optional;

import com.sgarcia.subcription.domain.Subcription;


public interface SubcriptionService {
	
    Subcription save(Subcription subcription);

    void delete(Long id);

    Optional<Subcription> get(Long id);

    List<Subcription> getAll();

}
