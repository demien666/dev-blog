package com.demien.sparktest;

import com.demien.sparktest.domain.Param;

public class ParamControllerIT extends GenericControllerIT<Param> {
    public ParamControllerIT() {
        super(App.PARAM_PATH, Param.class, App.paramService);
    }
}
