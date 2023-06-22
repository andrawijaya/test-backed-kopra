package com.kopra.service;

import com.kopra.entity.Profile;
import com.kopra.exception.EntityExistException;
import com.kopra.repository.IProfileRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService {

    private IProfileRepository profileRepository;

    public ProfileService(IProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
    @Override
    public Profile create(Profile data) throws Exception {
        try {
          return profileRepository.save(data);
        }catch (DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Profile> listProfile(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Profile> result = profileRepository.findAll(pageable);
        return result;
    }
}
