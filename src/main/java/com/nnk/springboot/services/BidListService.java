package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

@Service
public class BidListService extends CrudService<BidList> {

    BidListService(BidListRepository repository) {
        super(repository);
    }
}
