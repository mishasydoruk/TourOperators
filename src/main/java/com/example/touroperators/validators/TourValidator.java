package com.example.touroperators.validators;

import com.example.touroperators.dto.CreateTourDTO;
import com.example.touroperators.dto.UpdateTourDTO;
import com.example.touroperators.dto.UpdateUserDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.Tour;
import com.example.touroperators.repositories.TourOperatorReposirory;
import com.example.touroperators.repositories.TourRepository;
import com.example.touroperators.validators.Abstract.BaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourValidator implements BaseValidator {

    private final TourRepository tourRepository;
    private final TourOperatorReposirory tourOperatorReposirory;

    public void validateTourOperatorExists(CreateTourDTO createTourDTO) throws ServiceValidationError {

        boolean tourOperatorExists = tourOperatorReposirory
                .getTourOperatorById(createTourDTO.getTourOperatorId())!=null;

        if(!tourOperatorExists){
            throw new ServiceValidationError("tourOperatorId", "Tour operator not exists");
        }
    }

    public CreateTourDTO validateCreate(CreateTourDTO createTourDTO) throws ServiceValidationError {

        validateTourOperatorExists(createTourDTO);

        return createTourDTO;
    }

    public UpdateTourDTO validateUpdate(UpdateTourDTO updateTourDTO){

        //some validations... can't think of
        return updateTourDTO;
    }
}
