package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService extends CrudService<Trade>{
    TradeService(TradeRepository repository) {
        super(repository);
    }
}
