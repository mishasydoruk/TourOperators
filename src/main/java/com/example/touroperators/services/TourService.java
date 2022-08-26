package com.example.touroperators.services;

import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.Tour;
import com.example.touroperators.models.User;
import com.example.touroperators.repositories.TourRepository;
import com.example.touroperators.repositories.UserRepository;
import com.example.touroperators.services.Abstract.BaseService;
import com.example.touroperators.validators.TourValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService extends BaseService {

    private final TourRepository tourRepository;
    private final TourOperatorService tourOperatorService;
    private final TourValidator tourValidator;
    private final UserRepository userRepository;

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

        UpdateTourDTO validatedUpdateTourDTO = tourValidator.validateUpdate(updateTourDTO);

        modelMapper.map(validatedUpdateTourDTO, tourInDb);

        return tourRepository.save(tourInDb);
    }

    public List<Tour> deleteTourBuId(Long id){

        return tourRepository.deleteTourById(id);
    }

    public List<Tour> addTourToUser(User user, Tour tour){

        tour.getUsers().add(user);
        tourRepository.save(tour);

        user.getTours().add(tour);
        userRepository.save(user);

        return userRepository.save(user)
                .getTours()
                .stream().toList();
    }

    public List<Tour> removeUserFromTour(User user, Tour tour){

        tour.getUsers().remove(user);
        tourRepository.save(tour);

        user.getTours().remove(tour);
        userRepository.save(user);

        return userRepository.save(user)
                .getTours()
                .stream().toList();
    }
}
