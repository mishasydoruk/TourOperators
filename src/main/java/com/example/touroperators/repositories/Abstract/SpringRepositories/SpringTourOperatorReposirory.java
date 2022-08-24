package com.example.touroperators.repositories.Abstract.SpringRepositories;

import com.example.touroperators.models.TourOperator;
import org.springframework.data.repository.Repository;

public interface SpringTourOperatorReposirory extends Repository<TourOperator, Long> {

    TourOperator save(TourOperator tourOperator);

    TourOperator getTourOperatorById(Long id);

    TourOperator getTourOperatorByName(String name);

    TourOperator deleteTourOperatorById(Long id);
}

