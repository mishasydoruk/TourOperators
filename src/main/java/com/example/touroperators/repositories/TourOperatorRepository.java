package com.example.touroperators.repositories;

import com.example.touroperators.dto.CreateTourOperatorDTO;
import com.example.touroperators.dto.UpdateTourOperatorDTO;
import com.example.touroperators.models.TourOperator;
import com.example.touroperators.repositories.Abstract.BaseRepository;
import com.example.touroperators.repositories.Abstract.SpringRepositories.SpringTourOperatorReposirory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TourOperatorRepository extends BaseRepository {

    private final SpringTourOperatorReposirory springTourOperatorReposirory;

    public TourOperatorRepository(ModelMapper modelMapper, SpringTourOperatorReposirory springTourOperatorReposirory) {
        super(modelMapper);
        this.springTourOperatorReposirory = springTourOperatorReposirory;
    }

    public TourOperator create(CreateTourOperatorDTO createTourOperatorDTO){

        TourOperator tourOperator = modelMapper.map(createTourOperatorDTO, TourOperator.class);

        return springTourOperatorReposirory.save(tourOperator);
    }

    public TourOperator getTourOperatoById(Long id){

        return springTourOperatorReposirory.getTourOperatorById(id);
    }

    public TourOperator getTourOperatorByName(String name){

        return springTourOperatorReposirory.getTourOperatorByName(name);
    }

    public TourOperator updateTourOperator(TourOperator tourOperator, UpdateTourOperatorDTO updateTourOperatorDTO){

        modelMapper.map(updateTourOperatorDTO, tourOperator);

        return springTourOperatorReposirory.save(tourOperator);
    }

    public TourOperator deleteTourOperatorById(Long id){
        return springTourOperatorReposirory.deleteTourOperatorById(id);
    }

}
