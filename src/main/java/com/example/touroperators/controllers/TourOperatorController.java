package com.example.touroperators.controllers;

import com.example.touroperators.controllers.Abstract.BaseController;
import com.example.touroperators.dto.CreateTourOperatorDTO;
import com.example.touroperators.dto.GetTourOperatorDTO;
import com.example.touroperators.dto.UpdateTourOperatorDTO;
import com.example.touroperators.models.TourOperator;
import com.example.touroperators.services.TourOperatorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourOperatorController extends BaseController {

    private final TourOperatorService tourOperatorService;

    @PostMapping("tour-operator/")
    @SneakyThrows
    public ResponseEntity<GetTourOperatorDTO> createTourOperator(@Valid @RequestBody CreateTourOperatorDTO createTourOperatorDTO){

        TourOperator tourOperator = tourOperatorService.createTourOperator(createTourOperatorDTO);

        GetTourOperatorDTO getTourOperatorDTO = modelMapper.map(tourOperator, GetTourOperatorDTO.class);

        return new ResponseEntity<>(getTourOperatorDTO, HttpStatus.CREATED);
    }

    @GetMapping("tour-operator/{tourOperatorId}")
    public ResponseEntity<GetTourOperatorDTO> getTourOperator(@PathVariable Long tourOperatorId){

        TourOperator tourOperator = tourOperatorService.getTourOperatorById(tourOperatorId);

        if(tourOperator == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetTourOperatorDTO getTourOperatorDTO = modelMapper.map(tourOperator, GetTourOperatorDTO.class);

        return new ResponseEntity<>(getTourOperatorDTO, HttpStatus.OK);
    }

    @PutMapping("tour-operator/{tourOperatorId}")
    public ResponseEntity<GetTourOperatorDTO> updateTourOperator(@PathVariable Long tourOperatorId, @Valid @RequestBody UpdateTourOperatorDTO updateTourOperatorDTO){

        TourOperator tourOperator = tourOperatorService.getTourOperatorById(tourOperatorId);

        if(tourOperator==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TourOperator updatedTourOperator = tourOperatorService.updateTourOperator(tourOperator, updateTourOperatorDTO);

        GetTourOperatorDTO getTourOperatorDTO = modelMapper.map(updatedTourOperator, GetTourOperatorDTO.class);

        return new ResponseEntity<>(getTourOperatorDTO, HttpStatus.OK);

    }

    @DeleteMapping("tour-operator/{tourOperatorId}")
    public ResponseEntity<GetTourOperatorDTO> deleteTourOperator(@PathVariable Long tourOperatorId){

        List<TourOperator> tourOperators = tourOperatorService.deleteTourOperatorById(tourOperatorId);

        if(tourOperators.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GetTourOperatorDTO getTourOperatorDTO = modelMapper.map(tourOperators.get(0), GetTourOperatorDTO.class);

        return new ResponseEntity<>(getTourOperatorDTO, HttpStatus.OK);
    }

}
