package org.example.rukh.repository;

import org.example.rukh.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    @Query(value = "SELECT * FROM gallery g WHERE g.slider=true", nativeQuery = true)
    Gallery getSliderImage();
    @Query(value = "SELECT * FROM gallery g WHERE g.page=:page AND g.discipline=:tab", nativeQuery = true)
    Gallery getTopImage(@Param("page") String page,@Param("tab") String tab);
    boolean existsBySlider(boolean a);
    boolean existsByPageAndDiscipline(String page, String discipline);
}
