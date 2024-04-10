package com.AtelierJEE.JEEVIDEOCREATOR.service;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public interface CreatorService {
    public Creator AddCreator(Creator creator);

    public Creator findCreator(String email);
    public Optional<Creator> findCreator(Creator creator);
    public ResponseEntity<Creator> DeleteCreator(Long Id);
    public Creator ModifyCreator(Long id,Creator creator);
    public Collection<Creator> getAllCreators();
    public Creator getCreator(Long Id);

    Page<Creator> findCreatorByUsername(String name, int page, int size);
}
