package com.demien.guicetest.service;

import com.demien.guicetest.domain.Param;

import javax.inject.Singleton;

@Singleton
public class ParamService extends GenericService<Param, Long> {
    public ParamService() {
        super(Param.class);
    }
}
