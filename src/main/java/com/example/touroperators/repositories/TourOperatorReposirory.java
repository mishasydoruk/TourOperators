package com.example.touroperators.repositories;

import com.example.touroperators.models.TourOperator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TourOperatorReposirory extends Repository<TourOperator, Long> {

    TourOperator save(TourOperator tourOperator);

    TourOperator getTourOperatorById(Long id);

    TourOperator getTourOperatorByName(String name);

    @Transactional
    List<TourOperator> deleteTourOperatorById(Long id);
}

