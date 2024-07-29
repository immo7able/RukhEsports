package org.example.rukh.service;

import org.example.rukh.model.Gallery;
import org.example.rukh.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;
    public Gallery getSliderImage(){
        Gallery gallery=galleryRepository.getSliderImage();
        gallery.setImg("/uploads/"+gallery.getImg());
        return gallery;
    }
    public Gallery getTopImage(String page, String tab){
        Gallery gallery = galleryRepository.getTopImage(page, tab);
        gallery.setImg("/uploads/"+gallery.getImg());
        return gallery;
    }
}
