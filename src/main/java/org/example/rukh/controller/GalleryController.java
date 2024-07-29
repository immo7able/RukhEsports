package org.example.rukh.controller;

import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.model.Gallery;
import org.example.rukh.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class GalleryController {
    @Autowired
    private GalleryService galleryService;
    @GetMapping("/slider-image")
    public ResponseEntity<Gallery> getSliderImage() {
        Gallery gallery = galleryService.getSliderImage();
        return ResponseEntity.ok(gallery);
    }
    @GetMapping("/{page}/{tab}")
    public ResponseEntity<Gallery> getTopImage(@PathVariable("page")String page, @PathVariable("tab") String tab) {
        Gallery gallery = galleryService.getTopImage(page, tab);
        return ResponseEntity.ok(gallery);
    }
}
