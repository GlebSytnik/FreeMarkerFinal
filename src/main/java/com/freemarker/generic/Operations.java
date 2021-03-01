package com.freemarker.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

@Component
public class Operations {

    protected NamedParameterJdbcOperations operations;

    @Autowired
    public void setOperations(NamedParameterJdbcOperations operations) {
        this.operations = operations;
    }
}
