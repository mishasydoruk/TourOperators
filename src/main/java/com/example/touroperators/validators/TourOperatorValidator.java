package com.example.touroperators.validators;

import com.example.touroperators.dto.CreateTourOperatorDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.TourOperator;
import com.example.touroperators.repositories.TourOperatorReposirory;
import com.example.touroperators.validators.Abstract.BaseValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TourOperatorValidator implements BaseValidator {

    private final TourOperatorReposirory tourOperatorReposirory;

    public void validateAlreadyExists(CreateTourOperatorDTO createTourOperatorDTO) throws ServiceValidationError {
        boolean alreadyExists = tourOperatorReposirory
                .getTourOperatorByName(createTourOperatorDTO.getName())!=null;

        if(alreadyExists){
            throw new ServiceValidationError("name", "Tour operator already exists");
        }
    }

    public CreateTourOperatorDTO validateCreate(CreateTourOperatorDTO createTourOperatorDTO) throws ServiceValidationError {

        validateAlreadyExists(createTourOperatorDTO);

        return createTourOperatorDTO;
    }
}
