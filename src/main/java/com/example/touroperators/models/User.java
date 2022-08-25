package com.example.touroperators.models;

import com.example.touroperators.enums.TourType;
import com.example.touroperators.enums.UserRole;
import com.example.touroperators.models.Abstract.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@Slf4j
public class User extends BaseEntity {

    @NotNull @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @NotNull @NotBlank
    @Column(name = "lastname")
    private String lastName;

    @NotNull @NotBlank
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ManyToMany(mappedBy = "users", fetch= FetchType.EAGER)
    Set<Tour> tours = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="tour_operator_id")
    private TourOperator tourOperator;

    @PreRemove
    private void removeFromOtherTables(){
        tours.forEach(tour -> tour.getUsers().remove(this));

        if(tourOperator!=null)
        {
            tourOperator.getWorkers().remove(this);
        }
    }
}
