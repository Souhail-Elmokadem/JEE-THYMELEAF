package com.AtelierJEE.JEEVIDEOCREATOR.service;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.repositories.VideoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
public class VideoServiceImpl implements VideoService{
    @Autowired
    private VideoRepository Vrepo;
    @Override
    public Video addVideo(Video video) {
        return Vrepo.save(video);
    }

    @Override
    public Video deleteVideo(Long Id) {
        Optional<Video> Deletedvid =Vrepo.findById(Id);
        Vrepo.deleteById(Id);
        return Deletedvid.orElse(null);
    }

    @Override
    public Video getVideo(Long Id) {
        return Vrepo.findById(Id).orElseThrow(() -> new RuntimeException("Video is not found"));
    }

    @Override
    public Video modifyVideo(Long id, Video video) {
        Video v = new Video();
        BeanUtils.copyProperties(video,v);
        v.setId(id);
       return Vrepo.save(v);
    }

    @Override
    public Collection<Video> getAllVideos() {
        return Vrepo.findAll();
    }

    @Override
    public Optional<Video> getVideosByCreator(Creator creator) {
        return Vrepo.findByCreator(creator);
    }
    @Override
    public Page<Video> findVideoByName(String name, int size, int page) {
        return Vrepo.findByNameContaining(name, PageRequest.of(size,page));
    }
}
