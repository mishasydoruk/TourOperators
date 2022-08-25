package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.GetTourDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.models.Tour;
import com.example.touroperators.services.TourService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourController extends BaseController {

    private final TourService tourService;

    @PostMapping("tour/")
    @SneakyThrows
    ResponseEntity<GetTourDTO> createTour(@Valid @RequestBody CreateTourDTO createTourDTO){

        Tour tour = tourService.createTour(createTourDTO);

        GetTourDTO getTourDTO = modelMapper.map(tour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.CREATED);
    }

    @GetMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> getTour(@PathVariable Long tourId){

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetTourDTO getTourDTO = modelMapper.map(tour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }

    @PutMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> updateTour(@PathVariable Long tourId, @Valid @RequestBody UpdateTourDTO updateTourDTO){

        Tour tour = tourService.getTourById(tourId);

        if(tour==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Tour updatedTour = tourService.updateTour(tour, updateTourDTO);

        GetTourDTO getTourDTO = modelMapper.map(updatedTour, GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }

    @DeleteMapping("tour/{tourId}")
    ResponseEntity<GetTourDTO> deleteTour(@PathVariable Long tourId){

        List<Tour> tours = tourService.deleteTourBuId(tourId);

        if(tours.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetTourDTO getTourDTO = modelMapper.map(tours.get(0), GetTourDTO.class);

        return new ResponseEntity<>(getTourDTO, HttpStatus.OK);
    }
}

