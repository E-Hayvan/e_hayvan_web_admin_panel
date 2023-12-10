package com.production.ehayvanbackendapi.Repositories;

import com.production.ehayvanbackendapi.Entities.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {
}
