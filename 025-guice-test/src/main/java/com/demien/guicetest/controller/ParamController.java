package com.demien.guicetest.controller;

import com.demien.guicetest.App;
import com.demien.guicetest.domain.Param;
import com.demien.guicetest.service.ParamService;

import javax.inject.Inject;

public class ParamController extends GenericController<Param> {

    @Inject
    public ParamController(ParamService service) {
        super(App.PATH_PARAM, service);
    }
}
