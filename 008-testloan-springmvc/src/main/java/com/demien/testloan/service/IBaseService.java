package com.demien.testloan.service;

import java.util.List;

import com.demien.testloan.dao.IBaseDAO;

public interface IBaseService<T> extends IBaseDAO<T> {
  List<T> getAll();
  void deleteAll();
}
