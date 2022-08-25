package com.example.touroperators.repositories;

import com.example.touroperators.models.Tour;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TourRepository extends Repository<Tour, Long> {

    Tour save(Tour tour);

    Tour getTourById(Long id);

    Tour getTourByName(String name);

    @Transactional
    List<Tour> deleteTourById(Long id);
}
