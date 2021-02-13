package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.SubcriptionEntity;

public interface SubcriptionJpaRepository extends JpaRepository<SubcriptionEntity, Long> {

}
