package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

public class TradeService extends CrudService<Trade>{
    TradeService(TradeRepository repository) {
        super(repository);
    }
}
