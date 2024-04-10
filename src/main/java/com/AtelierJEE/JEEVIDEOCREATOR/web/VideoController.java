package com.AtelierJEE.JEEVIDEOCREATOR.web;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import com.AtelierJEE.JEEVIDEOCREATOR.service.VideoService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;


@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    VideoService Vservice;

    @GetMapping("/Allvideos")
    public String allVids(Model model,
                          @RequestParam(name = "Search", defaultValue = "")String kw,
                          @RequestParam(name = "size" , defaultValue = "5")int size,
                          @RequestParam(name = "page", defaultValue = "0") int page){
        Page<Video> pagevideo = Vservice.findVideoByName(kw, page, size);
        model.addAttribute("videos",pagevideo.getContent());
        model.addAttribute("pages",new int[pagevideo.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("Search",kw);
        return "videoList";
    }

    @GetMapping("/AddVideo")
    public String AddVideo(Model model){
        return "addvideo";
    }


    @GetMapping("/UpdateViewVideo/{id}")
    public String updateVideoView(@PathVariable("id") Long id,Model model){
        model.addAttribute("video", Vservice.getVideo(id));
        model.addAttribute("modelVideo",new Video());
        return "updateVideo";
    }

    @PostMapping("/updateVideo/{id}")
    public String updateVideo(@PathVariable("id") Long id,@ModelAttribute("video")  Video video){
        Vservice.modifyVideo(id,video);

        return "redirect:/videos/Allvideos";
    }

    @PostMapping("AddVideo")
    public String AddVideoPost(Model model,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "description") String desc,
                               @RequestParam(name = "imageurl") String imgurl,
                               @RequestParam(name = "url") String url){

        Video video = new Video();
        video.setName(name);
        video.setDescription(desc);
        video.setUrl(url);
        video.setImageUrl(imgurl);
        video.setDatePublication(new Date());
        Vservice.addVideo(video);
        return "redirect:/videos/Allvideos";
    }

    @GetMapping("/deleteVideo/{id}")
    public String Deletevideo(@PathVariable Long id){
         Vservice.deleteVideo(id);
        return "redirect:/videos/Allvideos";
    }



}
