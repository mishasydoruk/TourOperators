package com.example.touroperators.security.access.Abstract;

import org.springframework.security.core.Authentication;

public interface IPermissionResolver {

    boolean hasUserPermission(Authentication authentication, Long userId);

    boolean hasTourOperatorPermission(Authentication authentication, Long tourOperatorId);

    boolean hasTourPermission(Authentication authentication, Long tourId);
}
