package com.example.touroperators.repositories;

import com.example.touroperators.models.Tour;
import org.springframework.data.repository.Repository;

public interface TourRepository extends Repository<Tour, Long> {

    Tour save(Tour tour);

    Tour getTourById(Long id);

    Tour getTourByName(String name);

    Tour deleteTourById(Long id);
}
