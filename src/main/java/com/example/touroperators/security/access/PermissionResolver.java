package com.example.touroperators.security.access;

import com.example.touroperators.models.User;
import com.example.touroperators.security.UserDetails.UserDetailsImpl;
import com.example.touroperators.security.access.Abstract.IPermissionResolver;
import com.example.touroperators.services.TourOperatorService;
import com.example.touroperators.services.TourService;
import com.example.touroperators.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionResolver implements IPermissionResolver {

    private final UserService userService;

    private final TourService tourService;

    private final TourOperatorService tourOperatorService;


    @Override
    public boolean hasUserPermission(Authentication authentication, Long userId) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userService
                .getUserByUserName(userDetails.getUsername())
                .getId().equals(userId);
    }

    @Override
    public boolean hasTourOperatorPermission(Authentication authentication, Long tourOperatorId) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userService
                .getUserByUserName(userDetails.getUsername())
                .getTourOperator()
                .getId().equals(tourOperatorId);


    }

    @Override
    public boolean hasTourPermission(Authentication authentication, Long tourId) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();;

        return userService
                .getUserByUserName(userDetails.getUsername())
                .getTourOperator()
                .getId().equals(
                        tourService
                                .getTourById(tourId)
                                .getTourOperator()
                                .getId()
                );
    }
}
