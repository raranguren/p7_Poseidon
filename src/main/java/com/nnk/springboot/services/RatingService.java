package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

public class RatingService extends CrudService<Rating> {
    RatingService(RatingRepository repository) {
        super(repository);
    }
}
