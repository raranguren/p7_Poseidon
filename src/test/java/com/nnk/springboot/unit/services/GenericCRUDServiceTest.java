package com.nnk.springboot.unit.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenericCRUDServiceTest {

    @InjectMocks
    RatingService crudService;

    @Mock
    RatingRepository repository;

    @Test
    public void read_all() {
        crudService.readAll();
        verify(repository).findAll();
    }

    @Test
    public void read_by_id() {
        crudService.read(1);
        verify(repository).findById(1);
    }

    @Test
    public void create() {
        Rating object = new Rating("AAA","AAA","BB",12);
        crudService.create(object);
        verify(repository).save(any());
    }

    @Test
    public void update() {
        Rating object = new Rating("AAA","AAA","BB",12);
        object.setId(123);
        crudService.update(object);
        verify(repository).save(any());
    }

    @Test
    public void delete() {
        crudService.delete(123);
        verify(repository).deleteById(123);
    }

}
