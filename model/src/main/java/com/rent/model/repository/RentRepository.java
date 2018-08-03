package com.rent.model.repository;

import com.rent.model.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<RentEntity, Long> {

}
