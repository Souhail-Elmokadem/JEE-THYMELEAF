package com.AtelierJEE.JEEVIDEOCREATOR.web;


import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DTO.ApiResponse;
import com.AtelierJEE.JEEVIDEOCREATOR.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class CreatorApiController {
    @Autowired
    CreatorService creatorService;
    @GetMapping("/allCreators")
    public ApiResponse<Creator> creators(
            @RequestParam(name = "Search", defaultValue = "") String kw,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Creator> pageCreators = creatorService.findCreatorByUsername(kw, page, size);
        return new ApiResponse<>(pageCreators.getContent(),(int) pageCreators.getTotalElements());
    }

    @GetMapping("/allCreators/{id}")
    public Creator creator(@PathVariable("id") Long id){
        return creatorService.getCreator(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public Creator create(@RequestBody Creator creator){
        return creatorService.AddCreator(creator);
    }
    @PutMapping("/update/{id}")
    public Creator updateCreator(@PathVariable("id") Long id,@RequestBody Creator creator){
        return creatorService.ModifyCreator(id,creator);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCreator(@PathVariable("id") Long id) {
        creatorService.DeleteCreator(id);
        return ResponseEntity.ok().build();
    }
}
