package com.example.touroperators.models;

import com.example.touroperators.models.Abstract.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
@Getter
@Setter
public class Tour extends NamedEntity {

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "users_tours",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> invitedUsers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="tour_operator_id", nullable=false)
    private TourOperator tourOperator;

}
