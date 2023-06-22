package com.kopra.service;

import com.kopra.entity.Profile;
import org.springframework.data.domain.Page;

public interface IProfileService  {
    // create
    Profile create(Profile data) throws Exception;

    // get All With Pagination
    Page<Profile> listProfile(Integer page, Integer size, String direction, String sortBy) throws Exception;

}
