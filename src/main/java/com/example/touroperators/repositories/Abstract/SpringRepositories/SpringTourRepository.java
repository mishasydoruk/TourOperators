package com.example.touroperators.repositories.Abstract.SpringRepositories;

import com.example.touroperators.models.Tour;
import org.springframework.data.repository.Repository;

public interface SpringTourRepository extends Repository<Tour, Long> {

    Tour save(Tour tour);

    Tour getTourById(Long id);

    Tour getTourByName(String name);

    Tour deleteTourById(Long id);
}
