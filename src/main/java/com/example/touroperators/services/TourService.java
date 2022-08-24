package com.example.touroperators.services;

import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.Tour;
import com.example.touroperators.repositories.TourRepository;
import com.example.touroperators.services.Abstract.BaseService;
import com.example.touroperators.validators.TourValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService extends BaseService {

    private final TourRepository tourRepository;
    private final TourOperatorService tourOperatorService;
    private final TourValidator tourValidator;

    public Tour createTour(CreateTourDTO createTourDTO) throws ServiceValidationError {

        CreateTourDTO validatedCreateTourDTO = tourValidator.validateCreate(createTourDTO);

        Tour tour = modelMapper.map(validatedCreateTourDTO, Tour.class);

        tour.setTourOperator(
                tourOperatorService
                        .getTourOperatorById(
                                validatedCreateTourDTO.getTourOperatorId()
            ));

        return tourRepository.save(tour);
    }

    public Tour getTourById(Long id){

        return tourRepository.getTourById(id);
    }

    public Tour updateTour(Tour tourInDb, UpdateTourDTO updateTourDTO){

        modelMapper.map(updateTourDTO, tourInDb);

        return tourRepository.save(tourInDb);
    }

    public Tour deleteTourBuId(Long id){

        return tourRepository.deleteTourById(id);
    }

}
