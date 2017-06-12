package com.demien.testloan.service;

import com.demien.testloan.dao.IBaseDAO;
import com.demien.testloan.domain.User;

public class UserServiceImpl extends BaseServiceImpl<User> implements
IUserService {

    public UserServiceImpl(final IBaseDAO<User> dao) {
        super(User.class, dao);
    }

}
