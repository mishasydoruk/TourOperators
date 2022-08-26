package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.GetTourDTO;
import com.example.touroperators.dto.GetToursDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.exceptions.NotFoundException;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.Tour;
import com.example.touroperators.models.User;
import com.example.touroperators.services.TourService;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TourController extends BaseController {

    private final TourService tourService;

    private final UserService userService;

    @PostMapping("tour/")
    ResponseEntity<GetTourDTO> createTour(@Valid @RequestBody CreateTourDTO createTourDTO) throws ServiceValidationError {

        Tour tour = tourService.createTour(createTourDTO);

        GetTourDTO getTourDTO = modelMapper.map(tour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.CREATED);
    }

    @GetMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> getTour(@PathVariable Long tourId) throws NotFoundException {

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            throw new NotFoundException("tourId", "Not found");
        }

        GetTourDTO getTourDTO = modelMapper.map(tour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }

    @PutMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> updateTour(@PathVariable Long tourId, @Valid @RequestBody UpdateTourDTO updateTourDTO) throws NotFoundException {

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            throw new NotFoundException("tourId", "Not found");
        }

        Tour updatedTour = tourService.updateTour(tour, updateTourDTO);

        GetTourDTO getTourDTO = modelMapper.map(updatedTour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }

    @DeleteMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> deleteTour(@PathVariable Long tourId) throws NotFoundException {

        List<Tour> tours = tourService.deleteTourBuId(tourId);

        if(tours.size()==0){
            throw new NotFoundException("tourId", "Not found");
        }

        GetTourDTO getTourDTO = modelMapper.map(tours.get(0), GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }

    @PostMapping("tour/{tourId}/user/{userId}")
    public ResponseEntity<GetToursDTO> addUserToTour(@PathVariable Long tourId, @PathVariable Long userId) throws NotFoundException {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("userId", "Not found");
        }

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            throw new NotFoundException("tourId", "Not found");
        }

        List<Tour> tours = tourService.addTourToUser(user, tour);

        GetToursDTO getToursDTO = new GetToursDTO();

        getToursDTO.setTours(tours
                .stream()
                .map(t -> modelMapper.map(t, GetTourDTO.class))
                .collect(Collectors.toList())
        );

        return new ResponseEntity<>(getToursDTO, HttpStatus.OK);
    }

    @DeleteMapping("tour/{tourId}/user/{userId}")
    public ResponseEntity<GetToursDTO> removeUserFromTour(@PathVariable Long tourId, @PathVariable Long userId) throws NotFoundException {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("userId", "Not found");
        }

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            throw new NotFoundException("tourId", "Not found");
        }

        List<Tour> tours = tourService.removeUserFromTour(user, tour);

        GetToursDTO getToursDTO = new GetToursDTO();

        getToursDTO.setTours(tours
                .stream()
                .map(t -> modelMapper.map(t, GetTourDTO.class))
                .collect(Collectors.toList())
        );

        return new ResponseEntity<>(getToursDTO, HttpStatus.OK);
    }

    @GetMapping("tour/user/{userId}")
    ResponseEntity<GetToursDTO> getTourByUser(@PathVariable Long userId) throws NotFoundException {

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new NotFoundException("userId", "Not found");
        }

        List<Tour> tours = user.getTours().stream().toList();

        GetToursDTO getToursDTO = new GetToursDTO();

        getToursDTO.setTours(tours
                .stream()
                .map(t -> modelMapper.map(t, GetTourDTO.class))
                .collect(Collectors.toList())
        );

        return new ResponseEntity<>(getToursDTO, HttpStatus.OK);
    }



}

