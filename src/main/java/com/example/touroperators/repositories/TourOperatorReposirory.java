package com.example.touroperators.repositories;

import com.example.touroperators.models.TourOperator;
import org.springframework.data.repository.Repository;

public interface TourOperatorReposirory extends Repository<TourOperator, Long> {

    TourOperator save(TourOperator tourOperator);

    TourOperator getTourOperatorById(Long id);

    TourOperator getTourOperatorByName(String name);

    TourOperator deleteTourOperatorById(Long id);
}

