package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

public class RuleNameService extends CrudService<RuleName> {
    RuleNameService(RuleNameRepository repository) {
        super(repository);
    }
}
