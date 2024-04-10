package com.AtelierJEE.JEEVIDEOCREATOR.DAO.repositories;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
        public Creator findByEmail(String email);
        public Page<Creator> findByNameContaining(String kw, PageRequest pageable);
}
