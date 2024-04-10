package com.AtelierJEE.JEEVIDEOCREATOR.service;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.repositories.CreatorRepository;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.repositories.VideoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CreatorServiceImpl implements CreatorService{

    @Autowired
    CreatorRepository Crepo;
    @Autowired
    VideoRepository Vrepo;
    @Override
    public Creator AddCreator(Creator creator) {
        Creator cr = Crepo.findByEmail(creator.getEmail());

        if (cr !=null){
            throw new RuntimeException("Already Exist");
        }

        return Crepo.save(creator);
    }

    @Override
    public Creator findCreator(String email) {
        return Crepo.findByEmail(email);
    }

    @Override
    public Optional<Creator> findCreator(Creator creator) {
        return Crepo.findById(creator.getId());
    }

    @Override
    public ResponseEntity<Creator> DeleteCreator(Long Id) {
        Creator c = Crepo.findById(Id).orElse(null);
        Video v = Vrepo.findByCreator(c).orElse(null);
        if (v != null) {
            // Proceed with video found
            Vrepo.deleteById(v.getId());
        }
        Crepo.deleteById(Id);
        return ResponseEntity.ok().build();
    }


    @Override
    public Creator ModifyCreator(Long id,Creator creator) {
        Creator c = new Creator();
        BeanUtils.copyProperties(creator,c);
        c.setId(id);
        return Crepo.save(c);
    }

    @Override
    public Collection<Creator> getAllCreators() {
        return Crepo.findAll();
    }

    @Override
    public Creator getCreator(Long Id) {
        return Crepo.findById(Id).orElseThrow(() -> new RuntimeException("Creator is not found"));
    }

    @Override
    public Page<Creator> findCreatorByUsername(String name, int size, int page) {
        return Crepo.findByNameContaining(name, PageRequest.of(size,page));
    }
}
