package com.production.ehayvanbackendapi.Repositories;

import com.production.ehayvanbackendapi.Entities.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer> {

}
