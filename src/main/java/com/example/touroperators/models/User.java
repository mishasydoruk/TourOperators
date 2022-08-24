package com.example.touroperators.models;

import com.example.touroperators.models.Abstract.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class User extends BaseEntity {

    @NotNull @NotBlank
    private String userName;

    @NotNull @NotBlank
    private String lastName;

    @NotNull @NotBlank
    private String password;

    @ManyToMany(fetch= FetchType.EAGER)
    Set<Tour> tours = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="tour_operator_id")
    private TourOperator tourOperator;

}
