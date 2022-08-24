package com.example.touroperators.repositories;

import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.models.Tour;
import com.example.touroperators.repositories.Abstract.BaseRepository;
import com.example.touroperators.repositories.Abstract.SpringRepositories.SpringTourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TourRepository extends BaseRepository {

    private final SpringTourRepository springTourRepository;

    public TourRepository(ModelMapper modelMapper, SpringTourRepository springTourRepository) {
        super(modelMapper);
        this.springTourRepository = springTourRepository;
    }

    public Tour createTour(CreateTourDTO createTourDTO){

        Tour tour = modelMapper.map(createTourDTO, Tour.class);

        return springTourRepository.save(tour);
    }

    public Tour getTourById(Long id){
        return springTourRepository.getTourById(id);
    }

    public Tour getTourByName(String name){
        return springTourRepository.getTourByName(name);
    }

    public Tour updateTour(Tour tour, UpdateTourDTO updateTourDTO){

        modelMapper.map(updateTourDTO, tour);

        return springTourRepository.save(tour);
    }

    public Tour deleteTourById(Long id){
        return springTourRepository.deleteTourById(id);
    }

}

