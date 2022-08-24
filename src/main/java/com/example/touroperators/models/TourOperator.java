package com.example.touroperators.models;

import com.example.touroperators.models.Abstract.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tour_operators")
@Getter
@Setter
public class TourOperator extends NamedEntity {

    @OneToMany(mappedBy = "tourOperator")
    private Set<Tour> tours = new HashSet<>();

    @OneToMany(mappedBy = "tourOperator")
    private Set<User> workers = new HashSet<>();
}
