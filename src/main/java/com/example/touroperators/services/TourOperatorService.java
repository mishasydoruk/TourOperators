package com.example.touroperators.services;

import com.example.touroperators.dto.CreateTourOperatorDTO;
import com.example.touroperators.dto.UpdateTourOperatorDTO;
import com.example.touroperators.exceptions.ServiceValidationError;
import com.example.touroperators.models.TourOperator;
import com.example.touroperators.repositories.TourOperatorReposirory;
import com.example.touroperators.services.Abstract.BaseService;
import com.example.touroperators.validators.TourOperatorValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourOperatorService extends BaseService {

    private final TourOperatorReposirory tourOperatorReposirory;

    private final TourOperatorValidator tourOperatorValidator;

    public TourOperator createTourOperator(CreateTourOperatorDTO createTourOperatorDTO) throws ServiceValidationError {

        CreateTourOperatorDTO validatedCreateTourOperatorDTO = tourOperatorValidator.validateCreate(createTourOperatorDTO);

        TourOperator tourOperator = modelMapper.map(validatedCreateTourOperatorDTO, TourOperator.class);

        return tourOperatorReposirory.save(tourOperator);
    }

    public TourOperator getTourOperatorById(Long id){
        return tourOperatorReposirory.getTourOperatorById(id);
    }

    public TourOperator updateTourOperator(TourOperator tourOperatorInDb, UpdateTourOperatorDTO updateTourOperatorDTO)
    {
        modelMapper.map(updateTourOperatorDTO, tourOperatorInDb);

        return tourOperatorReposirory.save(tourOperatorInDb);
    }

    public List<TourOperator> deleteTourOperatorById(Long id){

        return tourOperatorReposirory.deleteTourOperatorById(id);
    }

}
