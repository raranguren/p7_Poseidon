package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService extends CrudService<CurvePoint> {
    CurvePointService(CurvePointRepository repository) {
        super(repository);
    }
}
