package com.sgarcia.subcription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgarcia.subcription.entity.SubcriptionEntity;

public interface SubcriptionJpaRepository extends JpaRepository<SubcriptionEntity, Long> {

}
